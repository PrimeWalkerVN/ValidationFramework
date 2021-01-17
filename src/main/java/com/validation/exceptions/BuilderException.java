package com.validation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BuilderException {
    List<ValidatorException> errors = new ArrayList<>();

    public List<ValidatorException> getErrors() {
        return errors;
    }

    public void addError(ValidatorException e) {
        errors.add(e);
    }
}
