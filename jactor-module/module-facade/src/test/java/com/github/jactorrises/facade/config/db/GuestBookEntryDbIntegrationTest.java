package com.github.jactorrises.facade.config.db;

import com.github.jactorrises.client.datatype.Name;
import com.github.jactorrises.facade.JactorModule;
import com.github.jactorrises.persistence.boot.HibernateConfiguration;
import com.github.jactorrises.persistence.boot.entity.guestbook.GuestBookEntryEntityImpl;
import com.github.jactorrises.persistence.client.GuestBookEntity;
import com.github.jactorrises.persistence.client.GuestBookEntryEntity;
import com.github.jactorrises.persistence.client.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

import static com.github.jactorrises.business.domain.AddressDomain.anAddress;
import static com.github.jactorrises.business.domain.GuestBookDomain.aGuestBook;
import static com.github.jactorrises.business.domain.GuestBookEntryDomain.aGuestBookEntry;
import static com.github.jactorrises.business.domain.PersonDomain.aPerson;
import static com.github.jactorrises.business.domain.UserDomain.aUser;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JactorModule.class, HibernateConfiguration.class})
@Transactional
public class GuestBookEntryDbIntegrationTest {

    @Resource(name = "sessionFactory") @SuppressWarnings("unused") // initialized by spring
    private SessionFactory sessionFactory;

    @Test public void willSaveBlogEntryEntityToThePersistentLayer() {
        Serializable id = session().save(aGuestBookEntry().with(aPersistedGuestBookTitled("my guest book")).withEntryAs("svada", "lada").build().getEntity());

        session().flush();
        session().clear();

        GuestBookEntryEntity guestBookEntry = session().get(GuestBookEntryEntityImpl.class, id);

        assertThat(guestBookEntry.getGuestBook().getTitle()).as("guest book.title").isEqualTo("my guest book");
        assertThat(guestBookEntry.getCreatedTime()).as("entry.createdTime").isNotNull();
        assertThat(guestBookEntry.getCreatorName()).as("entry.creatorName").isEqualTo(new Name("lada"));
        assertThat(guestBookEntry.getEntry()).as("entry.entry").isEqualTo("svada");
    }

    private GuestBookEntity aPersistedGuestBookTitled(@SuppressWarnings("SameParameterValue") String blogTitled) {
        GuestBookEntity guestBookEntry = aGuestBook().with(aPersistedUser()).withTitleAs(blogTitled).build().getEntity();
        session().save(guestBookEntry);
        return guestBookEntry;
    }

    private UserEntity aPersistedUser() {
        UserEntity userEntity = aUser().withUserNameAs("titten")
                .withPasswordAs("demo")
                .withEmailAddressAs("jactor@rises")
                .with(aPerson().withDescriptionAs("description")
                        .with(anAddress().withAddressLine1As("the streets")
                                .withCityAs("Dirdal")
                                .withCountryAs("NO")
                                .withZipCodeAs(1234)
                        )
                )
                .build().getEntity();

        session().save(userEntity);

        return userEntity;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
