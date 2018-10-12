package com.gitlab.jactor.rises.commons.builder;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MissingFields {
    private final List<String> fieldsMissing = new ArrayList<>();

    void failWhenWhenFieldsAreMissing() {
        if (!fieldsMissing.isEmpty()) {
            throw new IllegalStateException("Missing fields:\n- " + String.join("\n- ", fieldsMissing));
        }
    }

    public Optional<MissingFields> presentWhenFieldsAreMissing() {
        return fieldsMissing.isEmpty() ? Optional.empty() : Optional.of(this);
    }

    public void addInvalidFieldWhenBlank(String field, String value) {
        if (StringUtils.isBlank(value)) {
            fieldsMissing.add(field);
        }
    }

    public void addInvalidFieldWhenNoValue(String field, Object value) {
        if (value == null) {
            fieldsMissing.add(field);
        }
    }

    public void forEach(Consumer<? super String> fieldConsumer) {
        fieldsMissing.forEach(fieldConsumer);
    }
}
