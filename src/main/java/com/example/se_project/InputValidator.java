//@Author: Inas Hamad

package com.example.se_project;

import java.time.Instant;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public InputValidator(){}
    public boolean validateIsAlphaAndSpaces(String string)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches())
            return true;
        else
            return false;
    }
    public boolean validateLength(String string, int length)
    {
        if (string.length()<= length)
            return true;
        else
            return false;
    }
    public boolean validateMinLength(String string, int length)
    {
        if (string.length()>= length)
            return true;
        else
            return false;
    }

    public boolean validatePhoneNumber(String phone)
    {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches() && phone.length() == 10)
            return true;
        else
            return false;
    }
    public boolean validateDate(LocalDate date)
    {
        if (date.isAfter(LocalDate.now()))
            return false;
        else
            return true;
    }
    public Boolean validateDouble(String number, String errorMessage)
    {
        try{
            Double doub = Double.parseDouble(number);
            return true;
        }
        catch (NumberFormatException ex)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
            alert.show();
            return false;
        }

    }
    public boolean validateDayMonthYear(Date date)
    {
        if(date.getYear() < 3000 && date.getYear() > 1000
                && date.getMonth() <= 12 && date.getMonth() >=1
                && date.getDay() >=1 && date.getDay() <= 31)
            return true;
        else
            return false;
    }


    public boolean validateNotNegative(Double price)
    {
        if(price >= 0)
            return true;
        else
            return false;
    };
    public boolean validateFutureDate(Date date)
    {
        Instant now = Instant.now();
        java.util.Date compare = java.util.Date.from(now);
        java.util.Date enteredDate = new java.util.Date(date.getYear(), date.getMonth(), date.getDay());
        if(compare.before(enteredDate))
            return true;
        else
            return false;
    }
}
