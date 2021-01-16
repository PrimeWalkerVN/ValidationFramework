package com.validation.methods;

import com.validation.Validator;
import com.validation.annotations.Regex;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMethod implements Validator {

    private Pattern pattern = null;
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {

        Annotation []annotations = field.getDeclaredAnnotations();
        for (Annotation ano: annotations) {
            if (ano instanceof Regex) {
                Regex regex = (Regex) annotations[0];
                setPattern(Pattern.compile(regex.pattern(), Pattern.CASE_INSENSITIVE));
                try {
                    valid(value);
                }catch (ValidatorException e){
                    throw new ValidatorException(!regex.message().isBlank() ? regex.message() :"Field '" + field.getName() + "' " + e.getMessage());
                }
            }
        }

        return true;
    }

    @Override
    public boolean valid(Object value) {
        Matcher matcher = pattern.matcher(value.toString());
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        } else {
            throw new ValidatorException("is not match");
        }

    }


    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

}
