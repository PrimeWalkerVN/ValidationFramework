package com.validation.builder;

import com.validation.ObjectValidation;
import com.validation.Validator;

import java.util.regex.Pattern;

public interface IValidationBuilder {
    IValidationBuilder isNumber();
    IValidationBuilder notEmpty();
    IValidationBuilder notBlank();
    IValidationBuilder isAlpha();
    IValidationBuilder nonNull();
    IValidationBuilder maxValue(int value);
    IValidationBuilder minValue(int value);
    IValidationBuilder maxLength(int value);
    IValidationBuilder minLength(int value);
    IValidationBuilder matchRegex(Pattern pattern);
    IValidationBuilder custom(Validator validator);

    ObjectValidation build();
}
