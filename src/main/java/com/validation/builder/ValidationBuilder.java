package com.validation.builder;

import com.validation.ObjectValidation;
import com.validation.Validator;
import com.validation.methods.*;
import com.validation.methods.ObjectMethod.*;

import java.util.regex.Pattern;

public class ValidationBuilder implements IValidationBuilder {

    private Validator isNumber;
    private Validator notEmpty;
    private Validator notBlank;
    private Validator nonNull;
    private Validator maxValue;
    private Validator isAlpha;
    private Validator minValue;
    private Validator maxLength;
    private Validator minLength;
    private Validator matchRegex;
    private Validator custom;


    @Override
    public IValidationBuilder isNumber() {
        this.isNumber = new NumberMethod();
        return this;
    }

    @Override
    public IValidationBuilder notEmpty() {
        this.notEmpty = new EmptyMethod();
        return this;
    }

    @Override
    public IValidationBuilder notBlank() {
        this.notBlank = new BlankMethod();
        return this;
    }

    @Override
    public IValidationBuilder isAlpha() {
        this.isAlpha = new IsAlphaMethod();
        return this;
    }

    @Override
    public IValidationBuilder nonNull() {
        this.nonNull = new NullMethod();
        return this;
    }

    @Override
    public IValidationBuilder maxValue(int value) {
        this.maxValue = new MaxValueMethod(value);
        return this;
    }

    @Override
    public IValidationBuilder minValue(int value) {
        this.minValue = new MinValueMethod(value);
        return this;
    }

    @Override
    public IValidationBuilder maxLength(int value) {
        this.maxLength = new MaxLengthMethod(value);
        return this;
    }

    @Override
    public IValidationBuilder minLength(int value) {
        this.minLength = new MinLengthMethod(value);
        return this;
    }

    @Override
    public IValidationBuilder matchRegex(Pattern pattern) {
        this.matchRegex = new RegexMethod(pattern);
        return this;
    }

    @Override
    public IValidationBuilder custom(Validator validator) {
        this.custom = new CustomBuilderMethod(validator);
        return this;
    }

    @Override
    public ObjectValidation build() {
        return new ObjectValidation(this.isNumber, this.notEmpty, this.notBlank, this.nonNull, this.maxValue, this.minValue, this.maxLength, this.minLength, this.matchRegex, this.custom, this.isAlpha);
    }
}
