package com.validation.methods.ObjectMethod;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class MinLengthMethod implements Validator {
    private Integer minLength = 0;
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        return false;
    }

    @Override
    public boolean valid(Object value) {
        if(min(value, minLength)){
            return true;
        }else {
            throw new ValidatorException("is less than min length");
        }
    }

    public MinLengthMethod(Integer minLength) {
        this.minLength = minLength;
    }

    protected boolean min(Object value, double target) {
        if(value==null) return false;
        double val = value.toString().length();
        return val >= target;
    }
}
