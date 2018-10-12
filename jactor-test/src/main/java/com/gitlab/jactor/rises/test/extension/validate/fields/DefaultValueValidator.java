package com.gitlab.jactor.rises.test.extension.validate.fields;

import com.gitlab.jactor.rises.commons.builder.FieldValidation;
import com.gitlab.jactor.rises.commons.builder.MissingFields;
import com.gitlab.jactor.rises.commons.builder.ValidInstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class DefaultValueValidator {
    private final Map<Class<?>, List<FieldValue>> defaultValuesByClass;

    DefaultValueValidator(Map<Class<?>, List<FieldValue>> defaultValuesByClass) {
        this.defaultValuesByClass = defaultValuesByClass;
    }

    <T> Optional<MissingFields> provideDefaultValues(ValidInstance<T> validInstance, T bean) {
        if (defaultValuesByClass.containsKey(bean.getClass())) {
            Map<String, FieldValue> fieldValuesByFieldName = defaultValuesByClass.get(bean.getClass()).stream()
                    .collect(Collectors.toMap(FieldValue::getName, fv -> fv));

            FieldValidation.performWithStandardValidation(validInstance, bean)
                    .ifPresent(missingFields ->
                            missingFields.forEach(fieldName -> addDefaultValue(fieldValuesByFieldName.get(fieldName), bean))
                    );
        }

        return Optional.empty();
    }

    private void addDefaultValue(FieldValue fieldValue, Object bean) {
        String setterName = fieldValue.fetchNameOfSetter();
        List<Method> methods = Arrays.asList(bean.getClass().getMethods());
        Optional<Method> possibleSetter = find(setterName, methods);

        if (possibleSetter.isPresent()) {
            Method setter = possibleSetter.get();
            Object value = fieldValue.getValue();

            invoke(setter, bean, value);
        } else {
            List<String> methodNames = methods.stream()
                    .map(Method::getName)
                    .collect(toList());

            throw new IllegalStateException(String.format("Unable to find '%s' amongst metods: %s", setterName, methodNames));
        }
    }

    private Optional<Method> find(String setterName, List<Method> methods) {
        return methods.stream()
                .filter(method -> setterName.equals(method.getName()))
                .filter(method -> method.getParameterCount() == 1)
                .findFirst();
    }

    private void invoke(Method setter, Object bean, Object value) {
        try {
            setter.invoke(bean, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Unable to invoke %s on %s", setter, bean), e);
        }
    }
}
