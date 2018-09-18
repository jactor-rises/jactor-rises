package com.gitlab.jactor.rises.facade.rest;

import com.gitlab.jactor.rises.commons.datatype.Username;
import com.gitlab.jactor.rises.commons.dto.UserDto;
import com.gitlab.jactor.rises.model.service.UserRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("A DefaultUserRestService")
class DefaultUserRestServiceTest {

    private UserRestService userRestService;
    private @Mock RestTemplate restTemplateMock;

    @BeforeEach
    void initAndMock() {
        MockitoAnnotations.initMocks(this);
        userRestService = new DefaultUserRestService(restTemplateMock, "");
    }

    @DisplayName("should use RestTemplate GET to find a user")
    @Test void shouldUseRestTemplateGetToFindUser() {
        when(restTemplateMock.getForEntity(anyString(), eq(UserDto.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        userRestService.find(new Username("jactor"));

        verify(restTemplateMock).getForEntity(eq("/user/find/jactor"), eq(UserDto.class));
    }

    @DisplayName("should use RestTemplate GET to fetch a user")
    @Test void shouldUseRestTemplateGetToFetchUser() {
        when(restTemplateMock.getForEntity(anyString(), eq(UserDto.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        userRestService.fetch(1L);

        verify(restTemplateMock).getForEntity(eq("/user/get/1"), eq(UserDto.class));
    }

    @DisplayName("should exchange user values with HttpMethod.POST when saving")
    @Test void shouldExchangeUserValuesWithHttpPostWhenSaveOrUpdate() {
        when(restTemplateMock.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(UserDto.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        UserDto userDto = new UserDto();
        userRestService.saveOrUpdate(userDto);

        verify(restTemplateMock).exchange(
                eq("/user/persist"), eq(HttpMethod.POST), eq(new HttpEntity<>(userDto)), eq(UserDto.class)
        );
    }

    @DisplayName("should find all usernames")
    @Test void shouldFindAllUsernames() {
        when(restTemplateMock.getForEntity("/user/all/usernames", String[].class))
                .thenReturn(new ResponseEntity<>(new String[]{"turbo", "netti", "titten"}, HttpStatus.OK));

        List<String> allUsernames = userRestService.findAllUsernames();

        assertThat(allUsernames).containsExactly("turbo", "netti", "titten");
    }
}
