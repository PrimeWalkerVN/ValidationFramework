package com.validation.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseException {
    private ObjectException objectException;


    public boolean hasError() {
        return objectException.hasError();
    }

    public List<ValidatorException> getError(String field) {
        return objectException.getErrors(field);
    }

    public List<String> getErrorInString(String field) {
        List<ValidatorException> errorList = objectException.getErrors(field);
        return errorList.stream()
                .map(Throwable::getMessage)
                .collect(Collectors.toList());
    }

    public Map<String, List<ValidatorException>> getAllErrors() {
        return objectException.getAllErrors();
    }

    public ObjectException getObjectException() {
        return objectException;
    }

    public void setObjectException(ObjectException objectException) {
        this.objectException = objectException;
    }
}
