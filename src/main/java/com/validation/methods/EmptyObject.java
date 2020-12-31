package com.validation.methods;

import com.validation.Validator;
import com.validation.ValidatorException;
import java.lang.reflect.Field;

public class EmptyObject implements Validator {


    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        if(value==null){
            throw new ValidatorException("Field '" + field.getName() + "' is null");
        }
        if(value.toString().isEmpty()){
            throw new ValidatorException("Field '" + field.getName() + "' is empty");
        }
        return true;
    }
}
