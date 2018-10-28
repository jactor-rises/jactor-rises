package com.github.jactor.rises.facade.domain.person;

import com.github.jactor.rises.facade.domain.address.AddressDomain;
import com.github.jactor.rises.test.extension.validate.SuppressValidInstanceExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Set;

import static com.github.jactor.rises.facade.domain.address.AddressDomain.anAddress;
import static com.github.jactor.rises.facade.domain.person.PersonDomain.aPerson;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Java6Assertions.assertThat;

@DisplayName("A PersonBuilder")
class PersonBuilderTest {

    @RegisterExtension SuppressValidInstanceExtension suppressValidation = new SuppressValidInstanceExtension(
            Set.of(AddressDomain.class)
    );

    @DisplayName("should not build an instance without an address")
    @Test void willNotBuildPersonDomainWithoutAnAddress() {
        assertThatIllegalStateException().isThrownBy(() -> aPerson().withSurname("jacobsen").build())
                .withMessageContaining("address");
    }

    @DisplayName("should not build an instance without a surname")
    @Test void shouldNotBuildAnInstanceWitoutSurname() {
        assertThatIllegalStateException().isThrownBy(() -> aPerson().with(anAddress()).build())
                .withMessageContaining("surname");
    }

    @DisplayName("should build an instance when all required fields are set")
    @Test void willBuildPersonDomainWhenAllRequiredFieldsAreSet() {
        assertThat(
                aPerson()
                        .with(anAddress())
                        .withSurname("nevland")
                        .withDescription("description field only for coverage")
                        .withFirstName("anne (for coverage only")
                        .withLocale("no") // for coverage only
                        .build()
        ).isNotNull();
    }
}
