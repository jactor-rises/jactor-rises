package com.github.jactor.rises.facade.consumer;

import com.github.jactor.rises.commons.datatype.Name;
import com.github.jactor.rises.facade.domain.address.AddressBuilder;
import com.github.jactor.rises.facade.domain.guestbook.GuestBookDomain;
import com.github.jactor.rises.facade.domain.guestbook.GuestBookEntryDomain;
import com.github.jactor.rises.facade.domain.person.PersonDomain;
import com.github.jactor.rises.facade.domain.user.UserDomain;
import com.github.jactor.rises.facade.service.GuestBookDomainService;
import com.github.jactor.rises.test.extension.validate.SuppressValidInstanceExtension;
import com.github.jactor.rises.test.util.SpringBootActuatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.github.jactor.rises.facade.domain.address.AddressDomain.anAddress;
import static com.github.jactor.rises.facade.domain.guestbook.GuestBookDomain.aGuestBook;
import static com.github.jactor.rises.facade.domain.guestbook.GuestBookEntryDomain.aGuestBookEntry;
import static com.github.jactor.rises.facade.domain.person.PersonDomain.aPerson;
import static com.github.jactor.rises.facade.domain.user.UserDomain.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("The GuestBookDomainService")
@ExtendWith(SpringExtension.class)
@ExtendWith(SuppressValidInstanceExtension.class)
@SpringBootTest
class GuestBookDomainServiceIntegrationTest {

    private @Autowired GuestBookDomainService guestBookDomainService;

    @BeforeEach void assumeJactorPersistenceRunning() throws IOException {
        assumeTrue(
                SpringBootActuatorUtil.isServerRunning("http://localhost:1099/jactor-persistence-orm"),
                "Integration test can only run when server is 'UP'"
        );
    }

    @DisplayName("should save guest book with relations")
    @Test void shouldSaveGuestBookWithRelations() {
        AddressBuilder address = anAddress()
                .withAddressLine1("the streets")
                .withCity("Dirdal")
                .withCountry("NO")
                .withZipCode(1234);
        PersonDomain person = aPerson()
                .withDescription("description")
                .withSurname("jacobsen")
                .with(address)
                .build();
        UserDomain user = aUser()
                .withUsername(createUnique("titten"))
                .withEmailAddress("jactor@rises")
                .with(person)
                .build();

        Serializable id = guestBookDomainService.saveOrUpdate(
                aGuestBook().withTitle("my guest book").with(user).build()
        ).getDto().getId();

        GuestBookDomain guestBook = guestBookDomainService.find(id).orElse(aGuestBook().build());

        assertAll(
                () -> assertThat(guestBook.getTitle()).as("guest book title").isEqualTo("my guest book"),
                () -> assertThat(guestBook.getUser().getUsername()).as("guestBook.user.username")
                        .isEqualTo(user.getUsername())
        );
    }

    @DisplayName("should save guest book entry with relations")
    @Test void shouldSaveGuestBookEntryWithRelations() {
        UserDomain userDomain = aUser()
                .withUsername(createUnique("netti"))
                .withEmailAddress("jactor@rises")
                .with(aPerson()
                        .withDescription("description")
                        .withSurname("nevland")
                        .with(anAddress().withAddressLine1("the streets")
                                .withCity("Ålgård")
                                .withCountry("NO")
                                .withZipCode(1234)
                        )
                ).build();

        GuestBookDomain guestBookDomain = aGuestBook().with(userDomain).withTitle("my guest book").build();
        GuestBookEntryDomain guestBookEntryDomain = aGuestBookEntry().with(guestBookDomain).withEntry("svada", "lada").build();

        Serializable id = guestBookDomainService.saveOrUpdateEntry(guestBookEntryDomain).getId();

        GuestBookEntryDomain guestBookEntry = guestBookDomainService.findEntry(id).orElse(aGuestBookEntry().build());

        assertAll(
                () -> assertThat(guestBookEntry.getGuestBook().getTitle()).as("guest book.title").isEqualTo("my guest book"),
                () -> assertThat(guestBookEntry.getCreatedTime()).as("entry.createdTime").isNotNull(),
                () -> assertThat(guestBookEntry.getCreatorName()).as("entry.creatorName").isEqualTo(new Name("lada")),
                () -> assertThat(guestBookEntry.getEntry()).as("entry.entry").isEqualTo("svada")
        );
    }

    private String createUnique(String username) {
        return username + "@" + LocalDateTime.now();
    }

}
