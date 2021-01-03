package com.validation.methods.Ranges;

import com.validation.Validator;
import com.validation.annotations.RangeLength;
import com.validation.annotations.RangeValue;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

abstract class RangeComparator implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException("Field '" + field.getName() + "' is null");
        }
        Annotation[] annotations = field.getAnnotations();
        if (0 == annotations.length) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (RangeLength.class.equals(annotation.annotationType()) ) {
                RangeLength range = (RangeLength) annotation;
                if (min(value, range.min()) && max(value, range.max())) {
                    return true;
                } else {
                    throw new ValidatorException(!range.message().isBlank() ? range.message() : "Field " + field.getName() + " is not in range length");
                }
            }
            if(RangeValue.class.equals(annotation.annotationType())){
                try {
                    Double.parseDouble(value.toString());
                } catch (NumberFormatException e) {
                    throw new ValidatorException("Field " + field.getName() + " is not a number!");
                }
                RangeValue range = (RangeValue) annotation;
                if (min(value, range.min()) && max(value, range.max())) return true;
                else
                    throw new ValidatorException(!range.message().isBlank() ? range.message() : "Field " + field.getName() + " is not in range value");
            }
           continue;
        }
        return false;
    }


    protected abstract boolean min(Object value, double target);

    protected abstract boolean max(Object value, double target);
}
