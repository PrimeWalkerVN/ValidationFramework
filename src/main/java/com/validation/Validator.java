package com.validation;

import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public interface Validator {
    boolean valid(Field field, Object value) throws ValidatorException;
}
