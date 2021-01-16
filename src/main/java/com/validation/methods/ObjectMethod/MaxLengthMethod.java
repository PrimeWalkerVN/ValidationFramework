package com.validation.methods.ObjectMethod;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class MaxLengthMethod implements Validator {
    private Integer maxLength = Integer.MAX_VALUE;
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        return false;
    }

    @Override
    public boolean valid(Object value) {
        if(max(value,maxLength)){
            return true;
        }else {
                throw new ValidatorException("is greater than max length");
        }
    }

    public MaxLengthMethod(Integer maxLength) {
        this.maxLength = maxLength;
    }

    boolean max(Object value, double target) {
        if(value == null) return false;
        String string = value.toString();
        return (string.length() <= target);
    }
}
