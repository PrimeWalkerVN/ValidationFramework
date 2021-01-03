package com.validation;

import com.validation.methods.*;

public class ValidatorFactory {

    ValidatorFactory(){}

    public static Validator getEmptyMethod(){
        return new EmptyMethod();
    }

    public static Validator getDateMethod(){
        return new DateMethod();
    }

    public static Validator getBlankMethod(){
        return new BlankMethod();
    }

    public static Validator getNullMethod(){
        return new NullMethod();
    }

    public static Validator getCustomMethod(){
        return new CustomMethod();
    }

    public static Validator getNumberMethod(){
        return new NumberMethod();
    }

    public static Validator getRangeLengthMethod(){
        return new RangeLengthMethod();
    }

    public static Validator getRangeValueMethod(){
        return new RangeValueMethod();
    }

    public static Validator getRegexMethod(){
        return new RegexMethod();
    }

    public static Validator getStringMethod(){
        return new DateMethod();
    }

}
