package com.validation.methods.ObjectMethod;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class MinValueMethod implements Validator {
    private Integer minValue = 0;
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        return false;
    }

    @Override
    public boolean valid(Object value) {
        if(min(value, minValue)){
            return true;
        }else {
            throw new ValidatorException("is less than min value");
        }
    }

    public MinValueMethod(Integer minValue) {
        this.minValue = minValue;
    }

    protected boolean min(Object value, double target) {
        if(value==null) return false;
        double val = Double.parseDouble(value.toString());
        return val >= target;
    }
}
