package com.gitlab.jactor.rises.facade.consumer;

import com.gitlab.jactor.rises.commons.datatype.Username;
import com.gitlab.jactor.rises.commons.dto.UserDto;
import com.gitlab.jactor.rises.facade.domain.user.UserDomain;
import com.gitlab.jactor.rises.facade.service.UserDomainService;
import com.gitlab.jactor.rises.test.extension.validate.SuppressValidInstanceExtension;
import com.gitlab.jactor.rises.test.util.SpringBootActuatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.gitlab.jactor.rises.commons.dto.UserDto.aUser;
import static com.gitlab.jactor.rises.facade.domain.address.AddressDomain.anAddress;
import static com.gitlab.jactor.rises.facade.domain.person.PersonDomain.aPerson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("The UserDomainService")
@ExtendWith(SpringExtension.class)
@ExtendWith(SuppressValidInstanceExtension.class)
@SpringBootTest
class UserDomainServiceIntegrationTest {

    private @Autowired UserDomainService userDomainService;

    @BeforeEach void assumeJactorPersistenceRunning() throws IOException {
        assumeTrue(
                SpringBootActuatorUtil.isServerRunning("http://localhost:1099/jactor-persistence-orm"),
                "Integration test can only run when server is 'UP'"
        );
    }

    @DisplayName("should find user with username jactor")
    @Test void shouldFindJactor() {
        Optional<UserDto> possibleUser = userDomainService.find(new Username("jactor"));

        assertAll(
                () -> assertThat(possibleUser).as("possibleUser").isPresent(),
                () -> {
                    UserDto user = possibleUser.orElse(aUser().build());

                    assertThat(user.getPerson().getFirstName()).as("user.person.firstName").isEqualTo("Tor Egil");
                }
        );
    }

    @DisplayName("should find user with username tip")
    @Test void shouldFindTip() {
        Optional<UserDto> possibleUser = userDomainService.find(new Username("tip"));

        assertAll(
                () -> assertThat(possibleUser).as("possibleUser").isPresent(),
                () -> {
                    UserDto user = possibleUser.orElse(aUser().build());

                    assertThat(user.getPerson().getFirstName()).as("user.person.firstName").isEqualTo("Suthatip");
                }
        );
    }

    @DisplayName("should find usernames on active users")
    @Test void shouldFindUsernames() {
        List<String> usernames = userDomainService.findAllUsernames();

        assertThat(usernames).contains("jactor", "tip");
    }

    @DisplayName("should save user domain")
    @Test void shouldSaveUserDomain() {
        Username username = userDomainService.saveOrUpdate(
                UserDomain.aUser()
                        .withUsername(createUniqueUsername())
                        .withEmailAddress("jactor@rises")
                        .with(aPerson()
                                .withDescription("description")
                                .withSurname("jacobsen")
                                .with(anAddress()
                                        .withAddressLine1("the streets")
                                        .withCity("Sandefjord")
                                        .withCountry("NO")
                                        .withZipCode(1234)
                                )
                        ).build()
        ).getUsername();

        Optional<UserDto> possibleUser = userDomainService.find(username);

        assertAll(
                () -> assertThat(possibleUser).as("possibleUser").isPresent(),
                () -> {
                    UserDto userdto = possibleUser.orElse(aUser().build());

                    assertAll(
                            () -> assertThat(userdto.getEmailAddress()).as("user.emailAddress").isEqualTo("jactor@rises"),
                            () -> assertThat(userdto.getPerson().getDescription()).as("user.description").isEqualTo("description")
                    );
                }
        );
    }

    private String createUniqueUsername() {
        return "username@" + LocalDateTime.now();
    }
}
