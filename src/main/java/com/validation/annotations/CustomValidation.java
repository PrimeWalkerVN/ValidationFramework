package com.validation.annotations;

import com.validation.Validator;
import com.validation.customs.ShiroValidator;
import com.validation.methods.CustomMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomValidation {

    Class<? extends Validator>[] validatedBy();
}