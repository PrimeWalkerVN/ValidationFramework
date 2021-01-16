package com.validation.customs;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomValidator implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException{
        return false;
    }

    @Override
    public boolean valid(Object value) {
        return false;
    }
}
