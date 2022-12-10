package com.example.se_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginController {
    @FXML
    protected Label LoginText;
    @FXML
    protected ComboBox roleField;
    @FXML
    protected TextField usernameField;
    @FXML
    protected TextField passwordField;
    @FXML
    protected Button loginButton;
//role tracking
    static String FINAL_ROLE = new String();
    @FXML
    protected void onHelloButtonClick() {
        LoginText.setText("Welcome to JavaFX Application!");
    }

   @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        UserRoles role = checkRole(roleField.getValue().toString());
        String ROLE = role.toString();
        String DBPassword = getPasswordFromDB(usernameField.getText(), passwordField.getText());
        Boolean verified = verifyLogin(passwordField.getText(), DBPassword);
        if (verified)
        {
            FINAL_ROLE = ROLE;
            LoginText.setText("Login Successful");
            forwardUserToPage(role);
        }

        else
            LoginText.setText("Incorrect username or password");

   }

   public void handleOwnerLogin() throws IOException {
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Owner-view.fxml")));
       Stage window = (Stage)loginButton.getScene().getWindow();
       window.setScene(new Scene(root));
   }

    public void handlePurchaserLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Purchaser-view.fxml")));
        Stage window = (Stage)loginButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void handleSalesPersonLogin() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sales-person-view.fxml")));
        Stage window = (Stage)loginButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void handleInventoryManagerLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryManager-view.fxml")));
        Stage window = (Stage)loginButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


   public void forwardUserToPage(UserRoles role) throws IOException {
       switch (role)
       {
           case OWNER -> handleOwnerLogin();
           case SALES_PERSON -> handleSalesPersonLogin();

           case PURCHASER -> handlePurchaserLogin();

           case INVENTORY_MANAGER -> handleInventoryManagerLogin();
       }
   }


   public UserRoles checkRole(String r)
   {
       UserRoles role = null;
       switch (r)
       {
           case "Admin":
               role= UserRoles.ADMIN;
               break;
           case "Owner":
               role=UserRoles.OWNER;
               break;
           case "Purchaser":
               role = UserRoles.PURCHASER;
               break;
           case "Inventory Manager":
               role = UserRoles.INVENTORY_MANAGER;
               break;
           case "Sales Person":
               role = UserRoles.SALES_PERSON;
               break;
           case "Accountant":
               role = UserRoles.ACCOUNTANT;
               break;
       }
       return role;

   }
   public String getPasswordFromDB(String ID, String password) throws SQLException {
       DBConnection connection = new DBConnection();
       List<Map<String, Object>> results =  connection.getResults("select  password from users where id=" + ID);
       connection.closeConnection();
       return results.get(0).get("password").toString();
   }

   public boolean verifyLogin(String password, String DBpassword)
   {
       if (password.equals(DBpassword))
       {
           return true;
       }
       else
       {
           return false;
       }
   }





}