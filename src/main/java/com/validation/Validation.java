package com.validation;

import com.validation.annotations.Message;
import com.validation.annotations.Regex;
import com.validation.exceptions.ObjectException;
import com.validation.exceptions.ResponseException;
import com.validation.exceptions.ValidationException;
import com.validation.annotations.DateFormat;
import com.validation.annotations.NotEmpty;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.module.ResolutionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Validation {
    private static volatile Validation validationInstance;

    private final Map<Class, Validator> validatorMap = new HashMap<>();

    ValidationException validationErrors;


    private Validation(){
        this.validationErrors = new ValidationException();
        validatorMap.put(NotEmpty.class, ValidatorFactory.getEmptyMethod());
        validatorMap.put(DateFormat.class, ValidatorFactory.getDateMethod());
        validatorMap.put(Regex.class, ValidatorFactory.getRegexMethod());
    }
    public static synchronized Validation getInstance() {
        if (validationInstance == null) {
            validationInstance = new Validation();
        }
        return validationInstance;
    }

    /**
     *
     * @param object
     * @return
     */
    public ResponseException validate(Object object) {

        validationErrors.addInstance(object);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            validate(object, field);
        }
        return validationErrors.getErrors(object);
    }
    public void validate(Object object, Field field) {
        Annotation[] annotations= field.getAnnotations();
        for (Annotation annotation : annotations){
            if(validatorMap.containsKey(annotation.annotationType())){
                try{
                    Object value = getMethodGet(object, field).invoke(object);
                    try {
                        validatorMap.get(annotation.annotationType()).valid(field, value);
                    }catch (ValidatorException e){
                        System.out.println(e);
                        validationErrors.addError(object, field, e);
                    }
                }catch (ValidatorException | IllegalAccessException | InvocationTargetException e){
                    System.out.println(e);
                } 
            }
        }
    }


    public Method getMethodGet(Object object, Field field) {
        String name = field.getName();
        String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            Method getMethod = object.getClass().getDeclaredMethod(methodName);
            return getMethod;
        } catch (NoSuchMethodException | SecurityException ex) {
            return null;
        }
    }
}
