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

    public ResponseException validate(Object object){
        return validationStrategy.validate(object);
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }
}
