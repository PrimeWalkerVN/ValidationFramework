package com.validation;

import com.validation.annotations.NotEmpty;

public class Test3 {

    @NotEmpty
   private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
