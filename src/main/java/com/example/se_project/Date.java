//@Author: Inas Hamad

package com.example.se_project;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Date {
    private int day;
    private int month;
    private int year;

    public Date()
    {

    }
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay()
    {
        return day;
    }
    public int getMonth()
    {
        return month;
    }
    public int getYear()
    {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void convertToDate(DatePicker datePicker)
    {
         setYear(datePicker.getValue().getYear());
         setMonth(datePicker.getValue().getMonthValue());
         setDay(datePicker.getValue().getDayOfMonth());

    }
    public void convertDatePickerToDate(DatePicker datePicker)
    {
        setYear(datePicker.getValue().getYear());
        setMonth(datePicker.getValue().getMonthValue());
        setDay(datePicker.getValue().getDayOfMonth());

    }
    public void convertStringToDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDate formatted = (LocalDate) formatter.parse(date);

        this.day = formatted.getDayOfMonth();
        this.month = formatted.getMonthValue();
        this.year = formatted.getYear();
    }


    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }
}
