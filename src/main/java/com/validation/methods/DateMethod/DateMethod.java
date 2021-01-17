package com.validation.methods.DateMethod;

import com.validation.Validator;
import com.validation.annotations.DateFormat;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DateMethod implements Validator {

    private String pattern = "";

    public boolean validDateElements(int day, int month, int year) {
        int[] daysOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(day * month * year < 0) {
            throw new ValidatorException("is invalid date");
        } else {
            if(year < 0) {
                throw new ValidatorException("is invalid date");
            }
            if(month < 1 || month > 12) {
                throw new ValidatorException("is invalid date");
            }
            if(year%400 == 0 || (year%4 == 0 && year%100 != 0)) {
                if(month == 2 && day > 29) {
                    throw new ValidatorException("is invalid date");
                }
            } else {
                if(month == 2 && day > 28) {
                    throw new ValidatorException("is invalid date");
                }
            }
            if(month != 2 && day > daysOfMonths[month-1]){
                throw new ValidatorException("is invalid date");
            }
        }
        return true;
    }

    public boolean valid(Field field, Object value) throws ValidatorException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length > 0){
            DateFormat dateFormat = (DateFormat) annotations[0];
            setPattern(dateFormat.format());
            try {
                valid(value);
            }catch (ValidatorException e){
                throw new ValidatorException(!dateFormat.message().isBlank() ? dateFormat.message() : "Field '" + field.getName() + "' " + e.getMessage());
            }
        }else {
            throw new ValidatorException("Can't find pattern");
        }
        return true;
    }

    @Override
    public boolean valid(Object value) throws ValidatorException {

        Integer convertDay = -1;
        Integer convertMonth = -1;
        Integer convertYear = -1;

        Integer[] parsedResults;

        DateParser parser = new DateParser();
        parsedResults = parser.parse(value, this.pattern);

        convertDay = parsedResults[0];
        convertMonth = parsedResults[1];
        convertYear = parsedResults[2];

        validDateElements(convertDay, convertMonth, convertYear);

        return true;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String newPattern) {
        this.pattern = newPattern;
    }
}
