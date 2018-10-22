package com.gitlab.jactor.rises.facade.domain;

import com.gitlab.jactor.rises.commons.datatype.EmailAddress;
import com.gitlab.jactor.rises.commons.datatype.Username;

public interface User extends Persistent {

    Username getUsername();

    Person getPerson();

    EmailAddress getEmailAddress();

    default boolean isUserNameEmailAddress() {
        return getEmailAddress() != null && getEmailAddress().isSameAs(getUsername());
    }
}
