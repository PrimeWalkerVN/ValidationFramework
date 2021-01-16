package com.validation;

import com.validation.exceptions.ResponseException;

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

    public ResponseException validate(Object object){
        return validationStrategy.validate(object);
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }
}
