package com.validation.exceptions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException{
    private Map<Object, ObjectException> errorsMap;

    public ValidationException(){
        errorsMap = new HashMap<>();
    }

    public void addInstance(Object o){
        if(errorsMap.containsKey(o)){
            return;
        }else{
            errorsMap.put(o, new ObjectException(o));
        }
        return;
    }

    public ResponseException  getErrors(Object o){
        ResponseException response = new ResponseException();
        if (errorsMap.containsKey(o)){
             response.setObjectException(errorsMap.get(o));
             return response;
        }
        return null;
    }

    public void addError(Object o, Field field, ValidatorException e) {
        if (!errorsMap.containsKey(o)) {
            errorsMap.put(o, new ObjectException(o));
        }
        errorsMap.get(o).addError(field, e);
    }

}
