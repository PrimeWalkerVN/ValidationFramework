package com.validation;

import java.lang.reflect.Field;

public interface Validator {

    public void valid(Field field, Object value);
}
