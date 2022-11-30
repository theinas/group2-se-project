package com.example.se_project;

import java.sql.SQLException;

public class UserFactory {
    public static User createUser(UserRoles role,String lastName, String firstName, String password) throws SQLException {
        switch (role)
        {
            case OWNER -> {
                return new Owner(lastName, firstName, password);
            }
        }
        return null;
    }
}
