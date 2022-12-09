//@Author: Inas Hamad

package com.example.se_project;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
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
            return true;
        else
            return false;
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
}
