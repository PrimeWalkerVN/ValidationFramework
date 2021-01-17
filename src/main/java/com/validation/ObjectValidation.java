package com.validation;

import com.validation.exceptions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ObjectValidation implements ValidationStrategy {
    private Validator isNumber;
    private Validator notEmpty;
    private Validator notBlank;
    private Validator isAlpha;
    private Validator nonNull;
    private Validator maxValue;
    private Validator minValue;
    private Validator maxLength;
    private Validator minLength;
    private Validator matchRegex;
    private Validator custom;

    private final BuilderException builderErrors = new BuilderException();

    public ObjectValidation() {
    }

    public ObjectValidation(Validator isNumber, Validator notEmpty, Validator notBlank, Validator nonNull, Validator maxValue, Validator minValue, Validator maxLength, Validator minLength, Validator matchRegex, Validator custom, Validator isAlpha) {
        this.isNumber = isNumber;
        this.notEmpty = notEmpty;
        this.notBlank = notBlank;
        this.nonNull = nonNull;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.matchRegex = matchRegex;
        this.isAlpha = isAlpha;
        this.custom = custom;
    }


    @Override
    public ResponseException validate(Object object) {
        ObjectValidation obj = new ObjectValidation();
        Field []fields = obj.getClass().getDeclaredFields();
        for (Field field: fields) {
            try {
                Validator value = (Validator) getMethodGet(this, field).invoke(this);
                if(Objects.nonNull(value)){
                    value.valid(object);
                }
            }catch (ValidatorException e) {
                builderErrors.addError(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }catch (Exception e){
//                System.out.println(e);
            }
        }
        ResponseException responseException = new ResponseException();
        responseException.setBuilderException(this.builderErrors);
        return responseException;
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

    public Validator getIsNumber() {
        return isNumber;
    }

    public Validator getNotEmpty() {
        return notEmpty;
    }

    public Validator getNotBlank() {
        return notBlank;
    }

    public Validator getNonNull() {
        return nonNull;
    }

    public Validator getMaxValue() {
        return maxValue;
    }

    public Validator getMinValue() {
        return minValue;
    }

    public Validator getMaxLength() {
        return maxLength;
    }

    public Validator getMinLength() {
        return minLength;
    }

    public Validator getMatchRegex() {
        return matchRegex;
    }

    public Validator getIsAlpha() {
        return isAlpha;
    }

    public Validator getCustom() {
        return custom;
    }
}
