package com.validation.methods;

import com.validation.Validator;
import com.validation.annotations.DateFormat;
import com.validation.exceptions.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMethod implements Validator {

    @Override
    public boolean valid(Field field, Object value) throws ValidatorException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length > 0){
            DateFormat dateFormat = (DateFormat) annotations[0];
            String format = dateFormat.format();
            String seperator = "[^\\da-zA-Z]";
            String[] monthsArray = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December"};
            String[] parsedDate = value.toString().split(seperator);
            String[] parseFormat = format.toString().split(seperator);

            int convertDay = -1;
            int convertMonth = -1;
            int convertYear = -1;

            for (int i = 0; i < parseFormat.length; i++){
                    switch (parseFormat[i]){
                        case "MM":
                            try {
                                int data = Integer.parseInt(parsedDate[i]);
                                convertMonth = data;
                            } catch (NumberFormatException e) {
                                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                            }
                            break;
                        case "DD":
                            try {
                                int data = Integer.parseInt(parsedDate[i]);
                                convertDay = data;
                            } catch (NumberFormatException e) {
                                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                            }
                            break;
                        case "YY": case "YYYY":
                            try {
                                int data = Integer.parseInt(parsedDate[i]);
                                convertYear = data;
                            } catch (NumberFormatException e) {
                                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                            }
                            break;
                        case "Month":
                            for(int indexMonth = 0; indexMonth <= monthsArray.length; indexMonth++) {
                                if(indexMonth==monthsArray.length){
                                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
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
                                if(indexMonth==monthsArray.length){
                                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                                }
                                String monthName = monthsArray[indexMonth];
                                if(monthName.substring(0,2).equals(parsedDate[i])){
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
                                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                                }
                            } catch (NumberFormatException e) {
                                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                            }
                            break;
                        case "M":
                            try {
                                int data = Integer.parseInt(parsedDate[i]);
                                if(Integer.toString(data).equals(parsedDate[i])) {
                                    convertMonth = data;
                                } else {
                                    throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                                }
                            } catch (NumberFormatException e) {
                                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
                            }
                            break;
                    }
            }

            if(convertDay * convertMonth * convertYear < 0) {
                throw new ValidatorException("Field '" + field.getName() + "' is invalid date");
            }
            return true;
        }else {
            throw new ValidatorException("Can't find pattern");
        }
    }
}
