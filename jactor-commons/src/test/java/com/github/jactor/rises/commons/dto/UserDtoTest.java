package com.github.jactor.rises.commons.dto;

import com.github.jactor.rises.commons.dto.PersistentDto;
import com.github.jactor.rises.commons.dto.PersonDto;
import com.github.jactor.rises.commons.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A UserDto")
class UserDtoTest {

    @DisplayName("should have a copy constructor")
    @Test
    void shouldHaveCopyConstructor() {
        UserDto userDto = new UserDto();
        userDto.setEmailAddress("somewhere@time");
        userDto.setPerson(new PersonDto());
        userDto.setUsername("me");

        UserDto copied = new UserDto(userDto);

        assertAll(
                () -> assertThat(copied.getEmailAddress()).as("email address").isEqualTo(userDto.getEmailAddress()),
                () -> assertThat(copied.getPerson()).as("person").isEqualTo(userDto.getPerson()),
                () -> assertThat(copied.getUsername()).as("user name").isEqualTo(userDto.getUsername())
        );
    }

    @DisplayName("should give values to PersistentDto")
    @Test
    void shouldGiveValuesToPersistentDto() {
        UserDto persistentDto = new UserDto();
        persistentDto.setCreatedBy("jactor");
        persistentDto.setCreationTime(LocalDateTime.now());
        persistentDto.setId(1L);
        persistentDto.setUpdatedBy("tip");
        persistentDto.setUpdatedTime(LocalDateTime.now());

        PersistentDto copied = new UserDto(persistentDto);

        assertAll(
                () -> assertThat(copied.getCreatedBy()).as("created by").isEqualTo(persistentDto.getCreatedBy()),
                () -> assertThat(copied.getCreationTime()).as("creation time").isEqualTo(persistentDto.getCreationTime()),
                () -> assertThat(copied.getId()).as("id").isEqualTo(persistentDto.getId()),
                () -> assertThat(copied.getUpdatedBy()).as("updated by").isEqualTo(persistentDto.getUpdatedBy()),
                () -> assertThat(copied.getUpdatedTime()).as("updated time").isEqualTo(persistentDto.getUpdatedTime())
        );
    }

}
