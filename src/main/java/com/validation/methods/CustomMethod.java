package com.validation.methods;

import com.validation.Validator;
import com.validation.annotations.Regex;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length > 0){

        }else {
            throw new ValidatorException("Can't find pattern");
        }
        return false;
    }
}
