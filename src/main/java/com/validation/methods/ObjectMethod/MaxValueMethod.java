package com.validation.methods.ObjectMethod;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class MaxValueMethod implements Validator {
    private Integer maxValue = Integer.MAX_VALUE;
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        return false;
    }

    @Override
    public boolean valid(Object value) {
        if(max(value,maxValue)){
            return true;
        }else {
            throw new ValidatorException("is greater than max value " + maxValue);
        }
    }

    public MaxValueMethod(Integer maxValue) {
        this.maxValue = maxValue;
    }

    protected boolean max(Object value, double target) {
        if(value==null) return false;
        double val = value.toString().length();
        return val <= target;
    }

}
