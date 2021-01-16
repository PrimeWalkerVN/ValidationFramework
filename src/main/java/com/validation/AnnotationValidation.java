package com.validation;

import com.validation.annotations.*;
import com.validation.exceptions.ResponseException;
import com.validation.exceptions.ValidationException;
import com.validation.exceptions.ValidatorException;
import com.validation.methods.CustomMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationValidation implements ValidationStrategy{

    private final Map<Class, Validator> validatorMap = new HashMap<>();

    ValidationException validationErrors;


    public AnnotationValidation(){
        this.validationErrors = new ValidationException();
        validatorMap.put(NotEmpty.class, ValidatorFactory.getEmptyMethod());
        validatorMap.put(DateFormat.class, ValidatorFactory.getDateMethod());
        validatorMap.put(IsNumber.class, ValidatorFactory.getNumberMethod());
        validatorMap.put(IsBlank.class, ValidatorFactory.getBlankMethod());
        validatorMap.put(Regex.class, ValidatorFactory.getRegexMethod());
        validatorMap.put(RangeLength.class, ValidatorFactory.getRangeLengthMethod());
        validatorMap.put(RangeValue.class, ValidatorFactory.getRangeValueMethod());
        validatorMap.put(CustomValidation.class, ValidatorFactory.getCustomMethod());
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
                    } catch (NoSuchMethodException | InstantiationException | ClassNotFoundException e) {
                        e.printStackTrace();
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
