package com.validation.methods;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;

public class DateMethod implements Validator {

    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        System.out.println("=====Date=======");
        System.out.println("NameField = " + field.getName());
        System.out.println("value = " + value);
        System.out.println("================");
        return true;
    }
}
