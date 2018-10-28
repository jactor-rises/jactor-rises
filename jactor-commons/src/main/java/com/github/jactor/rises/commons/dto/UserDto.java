package com.github.jactor.rises.commons.dto;

import java.io.Serializable;
import java.util.Optional;

public class UserDto extends PersistentDto<Long> implements Serializable {
    private PersonDto person;
    private String emailAddress;
    private String username;

    public UserDto() {
        // empty, if usage of setters
    }

    public UserDto(UserDto user) {
        super(user);
        emailAddress = user.getEmailAddress();
        person = user.getPerson();
        username = user.getUsername();
    }

    public PersonDto getPerson() {
        return person;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static UserDtoBuilder aUser() {
        return new UserDtoBuilder();
    }

    public static class UserDtoBuilder {
        private PersonDto.PersonDtoBuilder personDtoBuilder;
        private String username;
        private String emailAddress;

        public UserDtoBuilder with(PersonDto.PersonDtoBuilder personDtoBuilder) {
            this.personDtoBuilder = personDtoBuilder;
            return this;
        }

        public UserDtoBuilder withUserName(String username) {
            this.username = username;
            return this;
        }

        public UserDtoBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setEmailAddress(emailAddress);
            Optional.ofNullable(personDtoBuilder).ifPresent(builder -> userDto.setPerson(personDtoBuilder.build()));

            return userDto;
        }
    }
}
