package com.validation.customs;

import com.validation.Validator;
import com.validation.exceptions.ValidatorException;
import com.validation.methods.CustomMethod;

import java.lang.reflect.Field;

public class PrimeNumberValidation implements Validator {


    public PrimeNumberValidation() {}

    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {

        int number = (int)value;
        for (int i = 2; i <= (int)Math.sqrt(number); i++) {
            if (number % i == 0) {
                throw new ValidatorException("Field '" + field.getName() + "' is not a prime number");
            }
        }
        return true;
    }
}
