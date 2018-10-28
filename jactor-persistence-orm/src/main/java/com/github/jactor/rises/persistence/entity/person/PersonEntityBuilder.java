package com.github.jactor.rises.persistence.entity.person;

import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;
import com.github.jactor.rises.persistence.entity.address.AddressEntity;
import com.github.jactor.rises.persistence.entity.address.AddressEntityBuilder;
import com.github.jactor.rises.persistence.entity.user.UserEntity;
import com.github.jactor.rises.persistence.entity.user.UserEntityBuilder;

import java.util.Optional;

public class PersonEntityBuilder extends AbstractBuilder<PersonEntity> {
    private PersonEntity personToBuild = new PersonEntity();
    private AddressEntity addressEntity;
    private String description;
    private String firstName;
    private String surname;
    private String locale;
    private UserEntity userEntity;

    PersonEntityBuilder() {
        super(PersonEntityBuilder::validate);
    }

    public PersonEntityBuilder with(AddressEntity entity) {
        addressEntity = entity;
        return this;
    }

    public PersonEntityBuilder with(AddressEntityBuilder addressEntityBuilder) {
        return with(addressEntityBuilder.build());
    }

    public PersonEntityBuilder with(UserEntityBuilder userEntityBuilder) {
        userEntity = userEntityBuilder.with(personToBuild).build();
        return this;
    }

    public PersonEntityBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PersonEntityBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonEntityBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonEntityBuilder withLocale(String locale) {
        this.locale = locale;
        return this;
    }

    @Override protected PersonEntity buildBean() {
        personToBuild.setAddressEntity(addressEntity);
        personToBuild.setDescription(description);
        personToBuild.setFirstName(firstName);
        personToBuild.setSurname(surname);
        personToBuild.setLocale(locale);
        personToBuild.setUserEntity(userEntity);

        if (userEntity != null && userEntity.getPerson() == null) {
            userEntity.setPersonEntity(personToBuild);
        }

        return personToBuild;
    }

    private static Optional<MissingFields> validate(PersonEntity personEntity, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenNoValue(PersonEntity.class.getSimpleName(), "addressEntity", personEntity.getAddressEntity());
        missingFields.addInvalidFieldWhenNoValue(PersonEntity.class.getSimpleName(), "surname", personEntity.getSurname());

        return missingFields.presentWhenFieldsAreMissing();
    }
}
