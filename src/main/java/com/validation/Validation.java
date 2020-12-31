package com.validation;

import com.validation.annotations.DateFormat;
import com.validation.annotations.NotEmpty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Validation {
    private static volatile Validation validationInstance;
    
    private final Map<Class, Validator> validatorMap = new HashMap<>();

    private Validation(){
        validatorMap.put(NotEmpty.class, ValidatorFactory.getEmptyObject());
        validatorMap.put(DateFormat.class, ValidatorFactory.getDateObject());
    }
    public static synchronized Validation getInstance() {
        if (validationInstance == null) {
            validationInstance = new Validation();
        }
        return validationInstance;
    }

    public void validate(Object object) {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            validate(object, field);
        }
    }
    public void validate(Object object, Field field) {
        Annotation[] annotations= field.getAnnotations();


        for (Annotation annotation : annotations){
            if(validatorMap.containsKey(annotation.annotationType())){
                try{
                    Object value = getMethodGet(object, field).invoke(object);
                    validatorMap.get(annotation.annotationType()).valid(field, value);
                }catch (Exception e){
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
