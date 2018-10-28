package com.github.jactor.rises.commons.dto;

import java.io.Serializable;
import java.util.Optional;

public class PersonDto extends PersistentDto<Long> implements Serializable {
    private AddressDto address;
    private String locale;
    private String firstName;
    private String surname;
    private String description;

    public PersonDto() {
        // empty, if usage of setters
    }

    PersonDto(PersonDto person) {
        super(person);
        address = person.getAddress();
        locale = person.getLocale();
        firstName = person.getFirstName();
        surname = person.getSurname();
        description = person.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getLocale() {
        return locale;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static PersonDtoBuilder aPerson() {
        return new PersonDtoBuilder();
    }

    public static class PersonDtoBuilder {
        private AddressDto.AddressDtoBuilder addressDtoBuilder;
        private String description;
        private String firstName;
        private String surname;

        public PersonDtoBuilder with(AddressDto.AddressDtoBuilder addressDtoBuilder) {
            this.addressDtoBuilder = addressDtoBuilder;
            return this;
        }

        public PersonDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public PersonDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonDtoBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonDto build() {
            PersonDto personDto = new PersonDto();
            personDto.setAddress(Optional.ofNullable(addressDtoBuilder).map(AddressDto.AddressDtoBuilder::build).orElse(null));
            personDto.setDescription(description);
            personDto.setFirstName(firstName);
            personDto.setSurname(surname);

            return personDto;
        }
    }
}
