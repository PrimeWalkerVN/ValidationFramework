package com.validation.methods.DateMethod;

import com.validation.exceptions.ValidatorException;

public class DateParser {

    public DateParser() {}

    public Integer[] parse(Object value, String pattern) throws ValidatorException {

        String seperator = "[^\\da-zA-Z]";

        String letters = "[\\da-zA-Z]";

        String[] monthsArray = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December"};

        String formatSeperator = String.join("", pattern.split(letters));
        String valueSeperator = String.join("", value.toString().split(letters));

        String[] parsedDate = value.toString().split(seperator);
        String[] parseFormat = pattern.split(seperator);

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
                            throw new ValidatorException("is invalid date");
                        }
                    } catch (NumberFormatException e) {
                        throw new ValidatorException("is invalid date");
                    }
                    break;
                case "DD":
                    try {
                        int data = Integer.parseInt(parsedDate[i]);
                        if(parsedDate[i].length() == 2) {
                            convertDay = data;
                        } else {
                            throw new ValidatorException("is invalid date");
                        }
                    } catch (NumberFormatException e) {
                        throw new ValidatorException("is invalid date");
                    }
                    break;
                case "YY": case "YYYY":
                    try {
                        convertYear = Integer.parseInt(parsedDate[i]);
                    } catch (NumberFormatException e) {
                        throw new ValidatorException("is invalid date");
                    }
                    break;
                case "Month":
                    for(int indexMonth = 0; indexMonth <= monthsArray.length; indexMonth++) {
                        if(indexMonth==monthsArray.length){
                            throw new ValidatorException("is invalid date");
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
                            throw new ValidatorException("is invalid date");
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
                            throw new ValidatorException("is invalid date");
                        }
                    } catch (NumberFormatException e) {
                        throw new ValidatorException("is invalid date");
                    }
                    break;
                case "M":
                    try {
                        int data = Integer.parseInt(parsedDate[i]);
                        if(Integer.toString(data).equals(parsedDate[i])) {
                            convertMonth = data;
                        } else {
                            throw new ValidatorException("is invalid date");
                        }
                    } catch (NumberFormatException e) {
                        throw new ValidatorException("is invalid date");
                    }
                    break;
                default:
                    break;
            }

        }
        return new Integer[]{convertDay, convertMonth, convertYear};
    }
}
