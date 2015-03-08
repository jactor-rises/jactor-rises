package nu.hjemme.business.domain.persistence;

import nu.hjemme.business.time.NowAsPureDate;
import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class BlogEntityTest {

    @Before
    public void mockNow() {
        new NowAsPureDate();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        UserEntity userEntity = new UserEntity();

        BlogEntity base = new BlogEntity();
        base.setTitle("title");
        base.setUserEntity(userEntity);

        BlogEntity equal = new BlogEntity(base);
        equal.setTitle("title");
        equal.setUserEntity(userEntity);

        BlogEntity notEqual = new BlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntity());

        assertTrue(new HashCodeMatching(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        BlogEntity base = new BlogEntity();
        base.setTitle("title");
        base.setUserEntity(new UserEntity());

        BlogEntity equal = new BlogEntity(base);

        BlogEntity notEqual = new BlogEntity();
        notEqual.setTitle("another title");
        notEqual.setUserEntity(new UserEntity());

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void skalHaTidspunktForOpprettelseSattVedBrukAvNoArgsConstructor() {
        BlogEntity testBlogEntity = new BlogEntity();
        assertThat("Opprettet tidspunkt: ", testBlogEntity.getCreated(), is(equalTo(
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        )));
    }

    @After
    public void removeNowAsPureDate() {
        NowAsPureDate.removeNowAsPureDate();
    }
}