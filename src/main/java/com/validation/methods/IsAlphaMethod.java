package com.validation.methods;

import com.validation.Validator;
import com.validation.annotations.IsAlpha;
import com.validation.annotations.Regex;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class IsAlphaMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        Annotation[]annotations = field.getDeclaredAnnotations();
        for (Annotation ano: annotations) {
            if (ano instanceof IsAlpha) {
                try {
                    valid(value);
                }catch (ValidatorException e){
                    throw new ValidatorException("Field '" + field.getName() + "' " + e.getMessage());
                }
            }
        }

        return true;
    }

    @Override
    public boolean valid(Object value) {
        if(value==null){
            throw new ValidatorException("is null");
        }

        String str = value.toString();
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i))){
                throw new ValidatorException("is not only alphabet");
            }
        }
        return true;
    }
}
