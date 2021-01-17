package com.validation;

import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public interface Validator {
    boolean valid(Field field, Object value) throws ValidatorException;
    boolean valid(Object value) throws ValidatorException;

}
