package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.PersonDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.db.AddressEntityImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class PersonDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildPersonDomainWithoutTheFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonDomainBuilder.THE_FIRST_NAME_CANNOT_BE_NULL);

        PersonDomainBuilder.init().appendLastName("some last name").appendAddress(new AddressEntityImpl()).build();
    }

    @Test
    public void willNotBuildPersonDomainWithAnEmptyFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        PersonDomainBuilder.init()
                .appendFirstName("")
                .appendLastName("some last name")
                .appendAddress(new AddressEntityImpl())
                .build();
    }

    public String hentFeilmeldingFraName() {
        String nameErrorMessage = null;

        try {
            new Name("");
        } catch (IllegalArgumentException iae) {
            nameErrorMessage = iae.getMessage();
        }

        return nameErrorMessage;
    }

    @Test
    public void willNotBuildPersonDomainWithoutTheLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonDomainBuilder.THE_LAST_NAME_CANNOT_BE_NULL);

        PersonDomainBuilder.init().appendFirstName("some first name").appendAddress(new AddressEntityImpl()).build();
    }

    @Test
    public void willNotBuildPersonDomainWithAnEmptyLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        PersonDomainBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("")
                .appendAddress(new AddressEntityImpl())
                .build();
    }

    @Test
    public void willNotBuildPersonDomainWithoutTheAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonDomainBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        PersonDomainBuilder.init().appendFirstName("some first name").appendLastName("some last name").build();
    }

    @Test
    public void willBuildPersonDomainWhenAllFieldsAreSet() throws Exception {
        PersonDomain personDomain = PersonDomainBuilder.init()
                .appendFirstName("some first name")
                .appendLastName("some last name")
                .appendAddress(new AddressEntityImpl())
                .build();

        assertThat("Person", personDomain, is(notNullValue()));
    }
}
