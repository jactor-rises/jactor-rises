package com.gitlab.jactor.rises.client.domain;

import com.gitlab.jactor.rises.client.datatype.Name;

import java.time.LocalDateTime;

public interface GuestBookEntry extends Persistent {

    GuestBook getGuestBook();

    LocalDateTime getCreatedTime();

    String getEntry();

    Name getCreatorName();
}
