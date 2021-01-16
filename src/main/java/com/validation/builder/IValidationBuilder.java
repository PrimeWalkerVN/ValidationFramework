package com.validation.builder;

import com.validation.ObjectValidation;

import java.util.regex.Pattern;

public interface IValidationBuilder {
    IValidationBuilder isNumber();
    IValidationBuilder notEmpty();
    IValidationBuilder notBlank();
    IValidationBuilder nonNull();
    IValidationBuilder maxValue(int value);
    IValidationBuilder minValue(int value);
    IValidationBuilder maxLength(int value);
    IValidationBuilder minLength(int value);
    IValidationBuilder matchRegex(Pattern pattern);

    ObjectValidation build();
}
