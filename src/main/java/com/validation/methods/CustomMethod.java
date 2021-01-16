package com.validation.methods;

import com.validation.Validator;

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

    }

}
