package com.validation;

import com.validation.annotations.DateFormat;
import com.validation.annotations.IsBlank;
import com.validation.annotations.IsNumber;
import com.validation.annotations.NotEmpty;
import com.validation.annotations.Regex;

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
    @Regex(pattern = "[0-9]", message = "hihihi")
    private String regex = "aggg";


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

    public String getRegex() {
        return regex;
    }

}
