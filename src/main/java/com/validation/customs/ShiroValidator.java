package com.validation.customs;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;
import com.validation.methods.CustomMethod;

import java.lang.reflect.Field;

public class ShiroValidator implements Validator {

    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        System.out.println("Shiro Validator");
        return false;
    }
}
