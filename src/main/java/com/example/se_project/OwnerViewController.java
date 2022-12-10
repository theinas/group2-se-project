//@Author: Inas Hamad

package com.example.se_project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OwnerViewController {

    private List<Map<String, Object>> vendorIDs = new ArrayList<>();
    private List<Map<String, Object>> vendorNames = new ArrayList<>();
    @FXML
    Button editCustomerButton;
    @FXML
    Button deleteCustomerButton;
    @FXML
    Button searchCustomerButton;
    //added display all items button that is common to every user as well as GUI interface
    @FXML
    Button DisplayAllItemsButton;

    @FXML
    Button createCustomerButton;
    @FXML
    Button createUserButton;
    @FXML
    Button updateUserButton;
    @FXML
    Button deleteUserButton;
    @FXML
    Button logoutButton;
    @FXML
    Button searchUserButton;


    @FXML
    public void initialize()throws SQLException {
        getVendorIDs();
        if(!vendorIDs.isEmpty()) {
            for (int i = 0; i < vendorIDs.size(); i++) {
                int x = Integer.parseInt(vendorIDs.get(0).get("ID").toString());
                displaySeasonalDiscount(x);
            }
        }

    }

    public void displaySeasonalDiscount(int ID) throws SQLException {
        String vendorName = getVendorName(ID);
        Alert discount = new Alert(Alert.AlertType.INFORMATION,  vendorName+"'s seasonal discounts are here!", ButtonType.CLOSE);
        discount.show();
    }

    public String getVendorName(int ID) throws SQLException {
        DBConnection connection = new DBConnection();
        vendorNames =  connection.getResults("select company_name from vendor where ID = "+ID+";");
        connection.closeConnection();
        return vendorNames.get(0).get("company_name").toString();

    }

    public void getVendorIDs()throws SQLException{
        String query = "select ID from vendor where seasonal_discount_start <  CAST( current_timestamp() AS Date ) ;";
        DBConnection connection = new DBConnection();
        vendorIDs =  connection.getResults(query);
        connection.closeConnection();
    }


    @FXML
    public void onCreateCustomerButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Create-customer-view.fxml"));
        Stage window = (Stage)createCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    @FXML
    public void onEditCustomerButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Edit-customer-view.fxml"));
        Stage window = (Stage)editCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDeleteCustomerButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Delete-customer-view.fxml"));
        Stage window = (Stage)deleteCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onSearchCustomerButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Search-customer-view.fxml"));
        Stage window = (Stage)searchCustomerButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onCreateUserProfileClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Create-user-profile.fxml"));
        Stage window = (Stage)createUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onUpdateUserProfileClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Update-user-profile.fxml"));
        Stage window = (Stage)updateUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onDeleteUserButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Delete-user-profile.fxml"));
        Stage window = (Stage)deleteUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }



    @FXML
    public void onlogoutButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage window = (Stage)logoutButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void onsearchUserButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Search-user-view.fxml"));
        Stage window = (Stage)searchUserButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }



    @FXML
    public void onDisplayAllItemsButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Display-all-items-view.fxml"));
        Stage window = (Stage)DisplayAllItemsButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

