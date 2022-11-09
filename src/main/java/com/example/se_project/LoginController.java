package com.example.se_project;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

   @FXML
    private ComboBox<UserRoles> userRolesComboBox ;

    @FXML
    protected UserRoles getUserRolesComboBoxItems(){
        for (UserRoles role: UserRoles.values())
        {
            return role;
        }
        return null;
    }
    

}