package com.validation.methods.ObjectMethod;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class CustomBuilderMethod implements Validator {
    Validator validator = null;
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        return false;
    }

    @Override
    public boolean valid(Object value) {
        if(validator.valid(value)){
            return true;
        }else {
            throw new ValidatorException("is invalid");
        }
    }

    public CustomBuilderMethod(Validator validator) {
        this.validator = validator;
    }
}
