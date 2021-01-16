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

    }

    public static synchronized Validation getValidationInstance() {
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


    public ResponseException validate(Object object){
        return validationStrategy.validate(object);

    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }
}
