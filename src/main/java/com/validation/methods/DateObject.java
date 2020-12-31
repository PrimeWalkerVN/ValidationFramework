package com.validation.methods;

import com.validation.Validator;

import java.lang.reflect.Field;

public class DateObject implements Validator {
    @Override
    public void valid(Field field, Object value) {
        System.out.println("=====Date=======");
        System.out.println("NameField = " + field.getName());
        System.out.println("value = " + value);
        System.out.println("================");
    }
}
