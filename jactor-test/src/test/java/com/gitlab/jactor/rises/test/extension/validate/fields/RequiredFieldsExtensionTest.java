package com.gitlab.jactor.rises.test.extension.validate.fields;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("A RequiredFieldsExtension")
class RequiredFieldsExtensionTest {

    @RegisterExtension
    RequiredFieldsExtension requiredFields = new RequiredFieldsExtension();

    @Nested
    @DisplayName("without required fields provided")
    class WithoutRequiredFieldsProvided {

    }

    @Nested
    @DisplayName("with missing required fields")
    class WithMissingRequiredFields {

    }

    @Nested
    @DisplayName("without required fields provided")
    class WithAllRequiredFieldsProvided {

    }
}