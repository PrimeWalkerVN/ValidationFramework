package com.validation;


import com.validation.annotations.*;
import com.validation.customs.PrimeNumberValidation;


<<<<<<< HEAD
=======


>>>>>>> 5ee2fecfca38471c407dbd996d690d2d9f5e50a0
public class Test {
    @NotEmpty
    @RangeLength(min = 5)
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

    @RangeValue(min = 20, max = 100)
    private double age = 99;

    public int getSoNguyenTo() {
        return soNguyenTo;
    }

    public void setSoNguyenTo(int soNguyenTo) {
        this.soNguyenTo = soNguyenTo;
    }

    @IsNumber
    @CustomValidation(validatedBy = PrimeNumberValidation.class)
    private int soNguyenTo = 98;





    @RangeValue(min = 20, max = 100)
    private double age = 99;



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

    public String getRegex() {
        return regex;
    }

<<<<<<< HEAD

=======
>>>>>>> 5ee2fecfca38471c407dbd996d690d2d9f5e50a0
    public double getAge() {
        return age;
    }

}
