package com.github.jactorrises.model.domain.user;

import com.github.jactorrises.model.Builder;
import com.github.jactorrises.model.domain.DomainValidater;
import com.github.jactorrises.model.domain.person.PersonBuilder;
import com.github.jactorrises.model.domain.person.PersonDomain;
import com.github.jactorrises.model.persistence.entity.user.UserEntityBuilder;

import static com.github.jactorrises.model.persistence.entity.user.UserEntity.aUser;

public final class UserBuilder extends Builder<UserDomain> {
    private final UserEntityBuilder userEntityBuilder = aUser();

    UserBuilder() {
        super(configureValidator());
    }

    public UserBuilder withUserName(String userName) {
        userEntityBuilder.withUserName(userName);
        return this;
    }

    public UserBuilder with(PersonDomain personDomain) {
        userEntityBuilder.with(personDomain.getEntity());
        return this;
    }

    public UserBuilder with(PersonBuilder personDomainBuilder) {
        return with(personDomainBuilder.build());
    }

    public UserBuilder withEmailAddress(String emailAddress) {
        userEntityBuilder.withEmailAddress(emailAddress);
        return this;
    }

    @Override
    protected UserDomain buildBean() {
        return new UserDomain(userEntityBuilder.build());
    }

    private static DomainValidater<UserDomain> configureValidator() {
        return new DomainValidater<UserDomain>() {

            @Override public void validate(UserDomain domain) {
                addIfInvalid(domain.getUserName() == null, "user name", FieldValidation.REQUIRED);
                addIfInvalid(domain.getPerson() == null, "person", FieldValidation.REQUIRED);
            }
        };
    }
}
