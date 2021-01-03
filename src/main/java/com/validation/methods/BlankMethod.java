package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class BlankMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException("Field '" + field.getName() + "' is blank.");
        }

        if (!(value instanceof String)) {
            throw new ValidatorException("Field '" + field.getName() + "' is blank.");
        }

        if (((String) value).isBlank()) {
            throw new ValidatorException("Field '" + field.getName() + "' is blank.");
        }

        return true;
    }
}
