package com.gitlab.jactor.rises.test.extension.validate.fields;

import com.gitlab.jactor.rises.commons.builder.FieldValidation;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RequiredFieldsExtension implements BeforeEachCallback, AfterEachCallback {

    private DefaultValueValidator defaultValueValidator = null;

    @Override public void afterEach(ExtensionContext extensionContext) {
        FieldValidation.useStandardValidation();
    }

    @Override public void beforeEach(ExtensionContext extensionContext) {
        if (defaultValueValidator != null) {
            FieldValidation.applyFieldValidator(defaultValueValidator::provideDefaultValues);
        } else {
            throw new AssertionError("RequiredFieldsExtension is not activated!");
        }
    }
}
