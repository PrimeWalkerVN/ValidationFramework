package com.validation.methods.Ranges;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class RangeLengthMethod extends RangeComparator {
    @Override
    protected boolean min(Object value, double target) {
        if(value == null) return false;
        String string = value.toString();
        if(target == 0 ) return true;

        return (string.length() >= target);
    }

    @Override
    protected boolean max(Object value, double target) {
        if(value == null) return false;
        String string = value.toString();
        return (string.length() <= target);
    }

}
