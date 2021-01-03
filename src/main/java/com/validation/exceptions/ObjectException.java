package com.validation.exceptions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectException {
   private Object object;

   private Map<Field, List<ValidatorException>> errors = new HashMap<>();

   ObjectException(Object o){
       this.object = o;

   }

    public Object getObject() {
        return object;
    }

    public Map<Field, List<ValidatorException>> getErrors() {
        return errors;
    }

    public List<ValidatorException> getErrors(Field field){
       return errors.get(field);
    }

    public void addError(Field field, ValidatorException e){
       if(errors.containsKey(field)){
           errors.get(field).add(e);
       }else {
           errors.put(field, List.of(e));
       }
    }
}
