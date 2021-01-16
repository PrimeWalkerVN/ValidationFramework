package com.validation;

import com.validation.annotations.*;
import com.validation.customs.ShiroValidator;
import com.validation.exceptions.ValidatorException;
import com.validation.methods.CustomMethod;

import java.lang.reflect.Field;

public class Test {

    @NotEmpty
    private String name="";

    @DateFormat(format="DD/MM/YY")
    private String date = "12/02/2020";



    @IsNumber
    private String cac = null;

    @IsNumber
    @IsBlank
    private String cac2 = "";





    @CustomValidation(validatedBy = {ShiroValidator.class})
    private String testsji;



    public String getTestsji() {
        return testsji;
    }

    public void setTestsji(String testsji) {
        this.testsji = testsji;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCac() {
        return cac;
    }

    public String getCac2() {
        return cac2;
    }




    //    @NotEmpty
//    private String address;
}
