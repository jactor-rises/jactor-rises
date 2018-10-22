package com.gitlab.jactor.rises.facade.domain;

import java.time.LocalDate;

public interface Blog extends Persistent {
    String getTitle();

    User getUser();

    LocalDate getCreated();
}
