package com.validation;

import com.validation.exceptions.ResponseException;

public interface ValidationStrategy {
    ResponseException validate(Object object);
}
