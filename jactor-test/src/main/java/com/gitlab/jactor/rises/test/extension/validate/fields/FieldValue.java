package com.gitlab.jactor.rises.test.extension.validate.fields;

class FieldValue {

    private final String name;
    private final Object value;
    private String setter;

    FieldValue(String name, Object value) {
        this(name, value, null);
    }

    FieldValue(String name, Object value, String setter) {
        this.name = name;
        this.value = value;
        this.setter = setter;
    }

    String fetchNameOfSetter() {
        if (setter != null) {
            return setter;
        }

        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    String getName() {
        return name;
    }

    Object getValue() {
        return value;
    }
}
