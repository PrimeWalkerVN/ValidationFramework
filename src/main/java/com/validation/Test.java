package com.validation;

import com.validation.annotations.*;

public class Test {
    @NotEmpty
    @RangeLength(min = 5)
    private String name="";

    @DateFormat(format="DD/MM/YY")
    private String date = "12/02/2020";

    @Regex(pattern = "[0-9]", message = "hihihi")
    private String regex = "aggg";

    @RangeValue(min = 20, max = 100)
    private double age = 99;


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getRegex() {
        return regex;
    }

    public double getAge() {
        return age;
    }
}
