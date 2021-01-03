package com.validation.exceptions;

import java.util.List;

public class ResponseException {
    private ObjectException objectException;


    public ObjectException getObjectException() {
        return objectException;
    }

    public void setObjectException(ObjectException objectException) {
        this.objectException = objectException;
    }
}
