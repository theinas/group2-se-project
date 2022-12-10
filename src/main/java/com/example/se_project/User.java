//@Author: Inas Hamad

package com.example.se_project;

import java.sql.SQLException;

public abstract class User {
    protected String lastName;
    protected String firstName;
    protected int ID;
    protected String password;
    public static UserRoles role;

    public abstract void modify();
    public abstract void delete();

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }

    public UserRoles getRole() {
        return role;
    }



}
