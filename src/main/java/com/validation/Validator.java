package com.validation;

import java.lang.reflect.Field;

public interface Validator {
    abstract boolean valid(Field field, Object value) throws ValidatorException;
}
