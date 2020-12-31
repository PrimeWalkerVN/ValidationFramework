package com.validation;

import com.validation.annotations.DateFormat;
import com.validation.annotations.NotEmpty;

public class Test {

    @NotEmpty
    private String name="123";

    @DateFormat(format="DD?MM?YY")
    private String date = "12/02/2020";

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
    //    @NotEmpty
//    private String address;
}
