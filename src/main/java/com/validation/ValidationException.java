package com.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException extends RuntimeException{

    private Map<Object, List<Exception>> errsHashMap = new HashMap<>();

    public List<Exception> getAttribute(Object o){
        return errsHashMap.getOrDefault(o, null);
    }

    public void addException(Object o, Exception e) {
        if(errsHashMap.containsKey(o)){
            errsHashMap.get(o).add(e);
        }
    }
}
