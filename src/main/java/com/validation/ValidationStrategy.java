package com.validation;

import com.validation.exceptions.ResponseException;

public interface ValidationStrategy {
    public ResponseException validate(Object object);
}
