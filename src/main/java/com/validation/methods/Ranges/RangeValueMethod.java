package com.validation.methods.Ranges;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class RangeValueMethod extends RangeComparator {

    @Override
    protected boolean min(Object value, double target) {
        if(value==null) return false;
        double val = Double.parseDouble(value.toString());
        return val >= target;
    }

    @Override
    protected boolean max(Object value, double target) {
        if(value==null) return false;
        double val = Double.parseDouble(value.toString());
        return val <= target;
    }
}
