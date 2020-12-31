package com.validation.methods;

import com.validation.Validator;

import java.lang.reflect.Field;

public class EmptyObject implements Validator {

    public void valid(Field field, Object value) {
        System.out.println("=====Empty=======");
        System.out.println("NameField = " + field.getName());
        System.out.println("value = " + value);
        System.out.println("================");
    }
}
