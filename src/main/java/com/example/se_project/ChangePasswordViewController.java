//@Author: Robert Tedeschi

package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChangePasswordViewController {
    List<Map<String, Object>> results;
    @FXML
    protected TextField oldPassTextField;
    @FXML
    protected TextField newPassTextField;
    @FXML
    protected TextField confirmPassTextField;
    @FXML
    protected TextField uidTextField;
    @FXML
    protected Button backToMainButton;
    @FXML
    protected Button confirmButton;
    @FXML
    protected Button logoutButton;
    @FXML
    protected Label error;

    private InputValidator validator = new InputValidator();

    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void confirmButtonOnClick()throws SQLException {

        if (isValid())
        {
            String query = buildPassChangeQuery();
            DBConnection connection = new DBConnection();
            connection.addEntryToDB(query);
            connection.closeConnection();
        }
        else
        {
            error.setText("\n Couldn't create new password");
        }
    }

    public String buildPassChangeQuery() {
        String query = "update users set password = '" +newPassTextField.getText()+ "' where ID = "+
                Integer.parseInt(uidTextField.getText())+ ";";
        return query;
    }

    public boolean isValid() throws SQLException {
        if (isoldpassMatching( Integer.parseInt(uidTextField.getText()) ,oldPassTextField.getText()) &&
                Objects.equals(newPassTextField.getText(), confirmPassTextField.getText()) &&
                ispassValid(newPassTextField.getText())) {
            return true;
        } else {
            return false;
        }
    }


    public boolean ispassValid(String pass) {
        if (validator.validateLength(pass, 16) && validator.validateMinLength(pass, 8)) {
            return true;
        } else {
            error.setText("\nPassword is not valid");
            return false;
        }
    }

    public boolean isoldpassMatching(int uid, String pass) throws SQLException{
        DBConnection connection = new DBConnection();
        String oldPass = null;
        results = connection.getResults("select password from users where ID = " +uid+";");
        if (results.size() != 0){
            oldPass = results.get(0).get("password").toString();
        }
        connection.closeConnection();
        if(Objects.equals(oldPass, pass)){
            return true;
        }
        else return false;

    }

    @FXML
    public void onlogoutButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage window = (Stage)logoutButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }






}
