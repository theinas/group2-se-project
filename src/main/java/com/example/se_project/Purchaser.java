package com.example.se_project;

/*
Developer: Tanni Dev
*/

import java.sql.SQLException;

public class Purchaser extends User{

    public Purchaser(String lastName, String firstName,String password) throws SQLException {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setRole(UserRoles.OWNER);
        addToDB(lastName, firstName, password);
    }



    public void addToDB(String ln, String fn, String password) throws SQLException {
        String query = "INSERT INTO users(last_name, first_name, password) values(" + ln+ ","+fn+","+password+");";
        DBConnection connection = new DBConnection();
        connection.addEntryToDB(query);
        connection.closeConnection();
    }


}
