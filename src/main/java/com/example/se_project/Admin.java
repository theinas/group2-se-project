//@Author: Robert Tedeschi

package com.example.se_project;

import java.sql.SQLException;

public class Admin extends User{

    public Admin(String lastName, String firstName,String password) throws SQLException {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setRole(UserRoles.ADMIN);
        addToDB(role, lastName, firstName, password);
    }

    public void addToDB(UserRoles role, String ln, String fn, String password) throws SQLException {
        String query = "INSERT INTO users(last_name, first_name, password, role) values('" + ln+ "','"+fn+"','"+password+"','" +role+ "');";

        DBConnection connection = new DBConnection();

        connection.addEntryToDB(query);
        connection.closeConnection();
    }
}
