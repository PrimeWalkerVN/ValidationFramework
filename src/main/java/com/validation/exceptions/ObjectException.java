package com.validation.exceptions;

import java.lang.reflect.Field;
import java.util.*;

public class ObjectException {
   private final Object object;

   private final Map<String, List<ValidatorException>> errors = new HashMap<>();

   public ObjectException(Object o){
       this.object = o;

   }

   public boolean hasError() {
       return !errors.isEmpty();
   }


    public Object getObject() {
        return object;
    }

    public Map<String, List<ValidatorException>> getAllErrors() {
        return errors;
    }


    public List<ValidatorException> getErrors(String field){
       return errors.get(field);
    }

    public void addError(String field, ValidatorException e){
       if(errors.containsKey(field)){
           errors.get(field).add(e);
       }else {
           errors.put(field, new ArrayList<>(List.of(e)));
       }
    }
}
