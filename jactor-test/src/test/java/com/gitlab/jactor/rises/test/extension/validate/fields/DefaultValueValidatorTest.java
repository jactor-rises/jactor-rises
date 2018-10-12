package com.gitlab.jactor.rises.test.extension.validate.fields;

import com.gitlab.jactor.rises.commons.builder.MissingFields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A DefaultValueValidator")
class DefaultValueValidatorTest {

    private HashMap<Class<?>, List<FieldValue>> valuesByClass = new HashMap<>();
    private DefaultValueValidator defaultValueValidator = new DefaultValueValidator(valuesByClass);

    @DisplayName("should set a default value with a setter")
    @Test void shouldSetDefaultValueWithSetter() {
        Object value = new Object();
        valuesByClass.put(Pojos.ObjPojo.class, singletonList(new FieldValue("objField", value, "setObjField")));
        Pojos.ObjPojo pojo = new Pojos.ObjPojo();

        Optional<MissingFields> missingFields = defaultValueValidator.provideDefaultValues(this::validateObjPojo, pojo);

        assertAll(
                () -> assertThat(missingFields).isNotPresent(),
                () -> assertThat(pojo.getObjField()).isSameAs(value)
        );
    }

    private Optional<MissingFields> validateObjPojo(Pojos.ObjPojo pojo, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenNoValue("objField", pojo.getObjField());

        return missingFields.presentWhenFieldsAreMissing();
    }

    @DisplayName("should set a default value by creating a setter for field name")
    @Test void shouldSetDefaultValueUsingFieldName() {
        Object value = "default value";
        valuesByClass.put(Pojos.StrPojo.class, singletonList(new FieldValue("strField", value)));
        Pojos.StrPojo pojo = new Pojos.StrPojo();

        Optional<MissingFields> missingFields = defaultValueValidator.provideDefaultValues(this::validateStrPojo, pojo);

        assertAll(
                () -> assertThat(missingFields).isNotPresent(),
                () -> assertThat(pojo.getStrField()).isSameAs(value)
        );
    }

    private Optional<MissingFields> validateStrPojo(Pojos.StrPojo pojo, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenBlank("strField", pojo.getStrField());

        return missingFields.presentWhenFieldsAreMissing();
    }
}