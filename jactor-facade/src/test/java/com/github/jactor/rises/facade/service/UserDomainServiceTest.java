package com.github.jactor.rises.facade.service;

import com.github.jactor.rises.commons.datatype.Username;
import com.github.jactor.rises.commons.dto.UserDto;
import com.github.jactor.rises.facade.consumer.PersistentUserConsumer;
import com.github.jactor.rises.test.extension.validate.SuppressValidInstanceExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.github.jactor.rises.commons.dto.UserDto.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("The UserDomainService")
class UserDomainServiceTest {

    private @InjectMocks UserDomainService testUserDomainService;
    private @Mock PersistentUserConsumer persistentUserConsumerMock;

    @BeforeEach void initMocking() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("should return an empty Optional when the PersistentUserConsumer cannot find a dto for the given user name")
    @Test void shouldNotFindUnknownUser() {
        Optional<UserDto> user = testUserDomainService.find(new Username("someone"));
        assertThat(user.isPresent()).isEqualTo(false);
    }

    @DisplayName("should find a user when the user name only differs in case")
    @ExtendWith(SuppressValidInstanceExtension.class)
    @Test void shouldFindUser() {
        when(persistentUserConsumerMock.find(new Username("jactor"))).thenReturn(Optional.of(aUser().build()));
        Optional<UserDto> optionalUser = testUserDomainService.find(new Username("JACTOR"));

        assertThat(optionalUser.isPresent()).isEqualTo(true);
    }

    @DisplayName("should fetch user by id")
    @ExtendWith(SuppressValidInstanceExtension.class)
    @Test void shouldFetchUserById() {
        when(persistentUserConsumerMock.fetch(1L)).thenReturn(Optional.of(aUser().build()));
        Optional<UserDto> optionalUser = testUserDomainService.fetch(1L);

        assertThat(optionalUser.isPresent()).isEqualTo(true);
    }
}
