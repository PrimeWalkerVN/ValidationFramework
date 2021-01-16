package com.validation;

import com.validation.exceptions.ResponseException;
import com.validation.exceptions.ValidationException;
import com.validation.exceptions.ValidatorException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ObjectValidation implements ValidationStrategy {
    private Validator isNumber;
    private Validator notEmpty;
    private Validator notBlank;
    private Validator nonNull;
    private Validator maxValue;
    private Validator minValue;
    private Validator maxLength;
    private Validator minLength;
    private Validator matchRegex;

    private ValidationException validationErrors;

    public ObjectValidation() {
        this.validationErrors = new ValidationException();
    }

    public ObjectValidation(Validator isNumber, Validator notEmpty, Validator notBlank, Validator nonNull, Validator maxValue, Validator minValue, Validator maxLength, Validator minLength, Validator matchRegex) {
        this.isNumber = isNumber;
        this.notEmpty = notEmpty;
        this.notBlank = notBlank;
        this.nonNull = nonNull;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.matchRegex = matchRegex;
    }


    @Override
    public ResponseException validate(Object object) {
//        validationErrors.addInstance(object);
        ObjectValidation obj = new ObjectValidation();
        Field []fields = obj.getClass().getDeclaredFields();
        for (Field field: fields) {
            try {
                Validator value = (Validator) getMethodGet(this, field).invoke(this);
                if(Objects.nonNull(value)){
                    value.valid(object);
                }
            }catch (ValidatorException e) {
                System.out.println(e);
//                validationErrors.addError(object, field, e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }catch (Exception e){
//                System.out.println(e);
            }
        }
//        return validationErrors.getErrors(object);
        return null;
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

    public ValidationException getValidationErrors() {
        return validationErrors;
    }
}
