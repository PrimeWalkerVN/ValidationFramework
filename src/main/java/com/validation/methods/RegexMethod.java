package com.validation.methods;

import com.validation.Validator;
import com.validation.annotations.Regex;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        Annotation [] annotations = field.getDeclaredAnnotations();
        if (annotations.length > 0){
            Regex regex = (Regex) annotations[0];
            Pattern pattern = Pattern.compile(regex.pattern(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(value.toString());
            boolean matchFound = matcher.find();
            if(matchFound) {
                return true;
            } else {
                throw new ValidatorException( regex.message() != null ? regex.message() : "Field " + field.getName() + " is not match");
            }
        }else {
            throw new ValidatorException("Can't find pattern");
        }
    }
}
