package com.validation;

import com.validation.methods.DateObject;
import com.validation.methods.EmptyObject;

public class ValidatorFactory {

    ValidatorFactory(){}

    public static Validator getEmptyObject(){
        return new EmptyObject();
    }

    public static Validator getDateObject(){
        return new DateObject();
    }
}
