package com.validation.methods;

import com.validation.Validator;
import com.validation.annotations.DateFormat;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DateMethod implements Validator {
    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length > 0){
            DateFormat dateFormat = (DateFormat) annotations[0];
            String format = dateFormat.format();
            String seperator = "[^\\da-zA-Z]";
            String letters = "[\\da-zA-Z]";
            String[] monthsArray = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December"};
            int[] daysOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            String formatSeperator = String.join("", format.split(letters));
            String valueSeperator = String.join("", value.toString().split(letters));
            if(!formatSeperator.equals(valueSeperator)) {
                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
            }
            String[] parsedDate = value.toString().split(seperator);
            String[] parseFormat = format.split(seperator);

            int convertDay = -1;
            int convertMonth = -1;
            int convertYear = -1;

            for (int i = 0; i < parseFormat.length; i++){
                switch (parseFormat[i]){
                    case "MM":
                        try {
                            int data = Integer.parseInt(parsedDate[i]);
                            if(parsedDate[i].length() == 2) {
                                convertMonth = data;
                            } else {
                                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                            }
                        } catch (NumberFormatException e) {
                            throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                        }
                        break;
                    case "DD":
                        try {
                            int data = Integer.parseInt(parsedDate[i]);
                            if(parsedDate[i].length() == 2) {
                                convertDay = data;
                            } else {
                                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                            }
                        } catch (NumberFormatException e) {
                            throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                        }
                        break;
                    case "YY": case "YYYY":
                        try {
                            convertYear = Integer.parseInt(parsedDate[i]);
                        } catch (NumberFormatException e) {
                            throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                        }
                        break;
                    case "Month":
                        for(int indexMonth = 0; indexMonth <= monthsArray.length; indexMonth++) {
                            if(indexMonth==monthsArray.length){
                                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                            }
                            String monthName = monthsArray[indexMonth];
                            if(monthName.equals(parsedDate[i])){
                                convertMonth = indexMonth + 1;
                                break;
                            }
                        }
                        break;
                    case "Mon":
                        for(int indexMonth = 0; indexMonth <= monthsArray.length; indexMonth++) {
                            if(indexMonth == monthsArray.length){
                                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                            }
                            String monthName = monthsArray[indexMonth];
                            if(monthName.substring(0,3).equals(parsedDate[i])){
                                convertMonth = indexMonth + 1;
                                break;
                            }
                        }
                        break;
                    case "D":
                        try {
                            int data = Integer.parseInt(parsedDate[i]);
                            if(Integer.toString(data).equals(parsedDate[i])) {
                                convertDay = data;
                            } else {
                                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                            }
                        } catch (NumberFormatException e) {
                            throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                        }
                        break;
                    case "M":
                        try {
                            int data = Integer.parseInt(parsedDate[i]);
                            if(Integer.toString(data).equals(parsedDate[i])) {
                                convertMonth = data;
                            } else {
                                throw new ValidatorException(String.format("Field '%s' is invalid date", field.getName()));
                            }
                        } catch (NumberFormatException e) {
                            throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + parseFormat[i]);
                }
            }

            if(convertDay * convertMonth * convertYear < 0) {
                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
            } else {
                if(convertYear < 0) {
                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                }
                if(convertMonth < 1 || convertMonth > 12) {
                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                }
                if(convertYear % 400 == 0 || (convertYear%4 == 0 && convertYear%100 != 0)) {
                    if(convertMonth == 2 && convertDay > 29) {
                        throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                    }
                } else {
                    if(convertMonth == 2 && convertDay > 28) {
                        throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                    }
                }
                if(convertMonth != 2 && convertDay > daysOfMonths[convertMonth-1]){
                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                }
            }
            return true;
        }else {
            throw new ValidatorException("Can't find pattern");
        }
    }
}
