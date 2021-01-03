package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {

        if (value == null) {
            throw new ValidatorException("Field '" + field.getName() + "' is not number.");
        }

        try {
            Number num = NumberFormat.getInstance().parse(value.toString());
            return true;
        }
        catch (ParseException e) {
            throw new ValidatorException("Field '" + field.getName() + "' is not number.");
        }
    }
}
