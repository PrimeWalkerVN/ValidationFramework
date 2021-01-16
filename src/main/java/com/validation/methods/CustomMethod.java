package com.validation.methods;

import com.validation.Validator;
<<<<<<< HEAD
import com.validation.annotations.CustomValidation;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        if (value == null) {
            throw new ValidatorException("Field '" + field.getName() + "' is null");
        }
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (0 == annotations.length) {
            return false;
        }

        for (Annotation ano:annotations) {
            if (ano instanceof CustomValidation){
                CustomValidation customValidator = (CustomValidation) ano;
                Class<? extends Validator> cls = customValidator.validatedBy();
                for (Method method : cls.getMethods()) {
                    if ("valid".equals(method.getName())) {
                        try{
                            method.invoke(cls.newInstance(),field,value);
                        }catch (InvocationTargetException e){
                            System.out.println(e.getTargetException());
                        }
                        break;
                    }
                }
            }
        }
        return true;
=======
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
>>>>>>> 5ee2fecfca38471c407dbd996d690d2d9f5e50a0
    }

}
