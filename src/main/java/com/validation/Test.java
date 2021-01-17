package com.validation;


import com.validation.annotations.*;
import com.validation.customs.PrimeNumberValidation;


public class Test {
    @NotEmpty
    @RangeLength(min = 5)
    private final String name = "";

    @DateFormat(format = "DD/MM/YY")
    private final String date = "12/02/2020";


    @IsNumber
    private final String asd = null;

    public String getAsd() {
        return asd;
    }

    public String getAsd2() {
        return asd2;
    }

    @IsNumber
    @IsBlank
    private final String asd2 = "";
    @Regex(pattern = "[0-9]", message = "hihihi")
    private final String regex = "aggg";

    @RangeValue(min = 20, max = 100)
    private final double age = 99;

    @IsNumber
    @CustomValidation(validatedBy = PrimeNumberValidation.class)
    private int soNguyenTo = 98;

    public int getSoNguyenTo() {
        return soNguyenTo;
    }

    public void setSoNguyenTo(int soNguyenTo) {
        this.soNguyenTo = soNguyenTo;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }



    //    @NotEmpty
//    private String address;

    public String getRegex() {
        return regex;
    }

    public double getAge() {
        return age;
    }

}
