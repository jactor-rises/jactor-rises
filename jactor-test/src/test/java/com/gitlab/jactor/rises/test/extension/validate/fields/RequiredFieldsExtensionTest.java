package com.gitlab.jactor.rises.test.extension.validate.fields;

import com.gitlab.jactor.rises.commons.builder.MissingFields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A RequiredFieldsExtension")
class RequiredFieldsExtensionTest {

    @RegisterExtension RequiredFieldsExtension requiredFields = new RequiredFieldsExtension(
            Map.of(Pojos.ObjPojo.class, List.of(new FieldValue("objField", new Object())))
    );

    @DisplayName("should not add required field when present")
    @Test void shouldNotAddRequiredFieldIWhenresent() {
        Object objField = new Object();
        Pojos.ObjPojo objPojo = new ObjPojoBuilder().with(objField).build();
        assertThat(objPojo.getObjField()).isSameAs(objField);
    }

    @DisplayName("should add required field when missing")
    @Test void shouldAddRequiredFieldWhenMissing() {
        Pojos.ObjPojo objPojo = new ObjPojoBuilder().build();
        assertThat(objPojo.getObjField()).isNotNull();
    }

    static Optional<MissingFields> validateObjPojoWithRequiredField(Pojos.ObjPojo objPojo, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenNoValue(Pojos.ObjPojo.class.getSimpleName(), "objField", objPojo.getObjField());
        return missingFields.presentWhenFieldsAreMissing();
    }
}