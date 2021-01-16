package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class BlankMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        try{
            valid(value);
        }catch (ValidatorException e){
            throw new ValidatorException("Field '" + field.getName() + "' " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean valid(Object value) {
        if (value == null) {
            throw new ValidatorException("is blank.");
        }

        if (!(value instanceof String)) {
            throw new ValidatorException("is blank.");
        }

        if (((String) value).isBlank()) {
            throw new ValidatorException("is blank.");
        }
        if(value.toString().isEmpty()){
            throw new ValidatorException("is empty");
        }
        return false;
    }
}
