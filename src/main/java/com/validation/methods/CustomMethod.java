package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class CustomMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        return false;
    }
}
