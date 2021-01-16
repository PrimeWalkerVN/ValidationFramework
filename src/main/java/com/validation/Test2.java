package com.validation;

import com.validation.annotations.DateFormat;
import com.validation.annotations.NotEmpty;

public class Test2 {

    @NotEmpty
    private final String name="";

    @DateFormat(format="DD/MM/YY")
    private final String date = "12/02/2020";

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
    //    @NotEmpty
//    private String address;
}
