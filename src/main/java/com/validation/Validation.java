package com.validation;






import com.validation.annotations.*;

import com.validation.exceptions.ObjectException;

import com.validation.annotations.*;
import com.validation.exceptions.ResponseException;
import com.validation.exceptions.ValidationException;
import com.validation.exceptions.ValidatorException;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class Validation {
    private static volatile Validation validationInstance;

    private ValidationStrategy validationStrategy;

    private Validation() {


    private Validation(){
        this.validationErrors = new ValidationException();
        validatorMap.put(NotEmpty.class, ValidatorFactory.getEmptyMethod());
        validatorMap.put(DateFormat.class, ValidatorFactory.getDateMethod());
        validatorMap.put(IsNumber.class, ValidatorFactory.getNumberMethod());
        validatorMap.put(IsBlank.class, ValidatorFactory.getBlankMethod());


        validatorMap.put(CustomValidation.class, ValidatorFactory.getCustomMethod());


        validatorMap.put(Regex.class, ValidatorFactory.getRegexMethod());

        validatorMap.put(RangeLength.class, ValidatorFactory.getRangeLengthMethod());
        validatorMap.put(RangeValue.class, ValidatorFactory.getRangeValueMethod());


        validatorMap.put(Regex.class, ValidatorFactory.getRegexMethod());
        validatorMap.put(RangeLength.class, ValidatorFactory.getRangeLengthMethod());
        validatorMap.put(RangeValue.class, ValidatorFactory.getRangeValueMethod());


    }

    public static synchronized Validation getValidationInstance() {
        if (validationInstance == null) {
            validationInstance = new Validation();
        }
        return validationInstance;
    }

<<<<<<< HEAD
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
                }catch (IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
            }
        }
=======
    public ResponseException validate(Object object){
        return validationStrategy.validate(object);
>>>>>>> 5ee2fecfca38471c407dbd996d690d2d9f5e50a0
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }
}
