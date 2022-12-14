package com.example.se_project;

import java.sql.SQLException;

public class SalesPerson extends User{

    public SalesPerson(String lastName, String firstName,String password) throws SQLException {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setRole(UserRoles.SALES_PERSON);
        addToDB(lastName, firstName, password, this.getRole().toString());
    }

    public void addToDB(String ln, String fn, String password, String role) throws SQLException {
        String query = "INSERT INTO users(last_name, first_name, password, role) values('" + ln+ "','"+fn+"','"+password+"','"+role+"');";

        DBConnection connection = new DBConnection();

        connection.addEntryToDB(query);
        connection.closeConnection();
    }
}
