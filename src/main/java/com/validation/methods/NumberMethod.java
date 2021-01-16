package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {

        try {
            valid(value);
        }catch (ValidatorException e){
            throw new ValidatorException("Field '" + field.getName() + "' " + e.getMessage());
        }

        return true;


    }

    @Override
    public boolean valid(Object value) {
        if (value == null) {
            throw new ValidatorException("is not number.");
        }
        try {
            NumberFormat.getInstance().parse(value.toString());
            return true;
        }
        catch (ParseException e) {
            throw new ValidatorException("is not number");
        }
    }
}
