package com.github.jactorrises.model.domain;

import com.github.jactorrises.client.datatype.Name;
import com.github.jactorrises.client.domain.Persistent;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class PersistentDomain<T extends Serializable> implements Persistent<T> {
    static final String THE_PERSISTENT_DATA_ON_THE_DOMAIN_CANNOT_BE_NULL = "The persistent data the domain cannot be null!";

    private Persistent<T> fetchDto() {
        Persistent<T> dto = getDto();
        Validate.notNull(dto, THE_PERSISTENT_DATA_ON_THE_DOMAIN_CANNOT_BE_NULL);

        return dto;
    }

    public abstract Persistent<T> getDto();

    @Override public T getId() {
        return fetchDto().getId();
    }

    @Override public Name getCreatedBy() {
        return fetchDto().getCreatedBy();
    }

    @Override public LocalDateTime getCreationTime() {
        return fetchDto().getCreationTime();
    }

    @Override public Name getUpdatedBy() {
        return fetchDto().getUpdatedBy();
    }

    @Override public LocalDateTime getUpdatedTime() {
        return fetchDto().getUpdatedTime();
    }
}
