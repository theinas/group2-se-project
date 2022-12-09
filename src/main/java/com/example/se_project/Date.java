//@Author: Inas Hamad

package com.example.se_project;

import javafx.scene.control.DatePicker;

public class Date {
    private int day;
    private int month;
    private int year;

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

    @Override
    public String toString()
    {
        return(month + "/" + day + "/" + year);
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

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }
}
