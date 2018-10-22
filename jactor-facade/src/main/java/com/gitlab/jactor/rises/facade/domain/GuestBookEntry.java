package com.gitlab.jactor.rises.facade.domain;

import com.gitlab.jactor.rises.commons.datatype.Name;

import java.time.LocalDateTime;

public interface GuestBookEntry extends Persistent {

    GuestBook getGuestBook();

    LocalDateTime getCreatedTime();

    String getEntry();

    Name getCreatorName();
}

