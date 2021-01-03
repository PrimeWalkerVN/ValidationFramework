package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class NullMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        if(value==null){
            throw new ValidatorException("Field '" + field.getName() + "' is null");
        }
        return true;
    }
}
