package com.example.se_project;
//Joshua
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static com.example.se_project.LoginController.FINAL_ROLE;

public class AddItemViewController {
    //declare members to mimic item class
    private String errorMessage = "";
    private String name;
    private int vendorID;
    private double salePrice;
    private Categories category;
    private Date expiration;
    private double purchasePrice;
    private Measurements unit;
    private double quantityOnHand;
    private InputValidator isValid = new InputValidator();
    //create text fields for all necessary inputs
    @FXML
    protected TextField nameTextField;
    @FXML
    protected ComboBox<Map<Integer, String>> vendorIDComboBox;
    @FXML
    protected TextField salePriceTextField;
    @FXML
    protected ComboBox<Categories> categoryComboBox;
    @FXML
    protected DatePicker expirationDatePicker;
    @FXML
    protected TextField purchasePriceTextField;
    @FXML
    protected ComboBox<Measurements> unitComboBox;
    @FXML
    protected TextField quantityOnHandTextField;
    //create buttons to create item and return to home screen
    @FXML
    protected Button createItemButton;
    @FXML
    protected Label displayError;
    @FXML
    protected Button backToMainButton;

    //fill combo boxes with enum values and retrieve vendor info from database
    public void initialize() throws SQLException {
        categoryComboBox.getItems().addAll(Categories.values());
        unitComboBox.getItems().addAll(Measurements.values());
        initializeDB();
    }

    @FXML
    public void createItemButtonOnClick() throws SQLException {
        saveValues();
        if (isValid())
        {
            Item newItem = new Item(name, vendorID, salePrice, category, expiration, purchasePrice,
                    unit, quantityOnHand);
            addItemToDB();
            updateErrorLabel(name + " was added Successfully");
        }
        else
        {
            updateErrorLabel(errorMessage);
        }
    }

    @FXML
    public void onBackToMainButtonClick() throws IOException {
        if (FINAL_ROLE.equals("OWNER")) {
            Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } else if (FINAL_ROLE.equals("PURCHASER")) {
            Parent root = FXMLLoader.load(getClass().getResource("Purchaser-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } else if (FINAL_ROLE.equals("INVENTORY_MANAGER")) {
            Parent root = FXMLLoader.load(getClass().getResource("InventoryManager-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }


    public boolean isNameValid(String name) {
        if (isValid.validateIsAlphaAndSpaces(name) && isValid.validateLength(name, 20)) {
            return true;
        } else {
            errorMessage += "\nItem name is not valid";
            return false;
        }
    }

    public boolean isPurchasePriceValid(double purchasePrice) {
        if (isValid.validateNotNegative(purchasePrice)) {
            return true;
        } else {
            errorMessage += "\nPrice cannot be negative";
            return false;}
    }

    public boolean isExpirationValid(LocalDate ex) {
        if (isValid.validateDate(ex))
            return true;
        else {
            errorMessage += "\n Date must be in the future";
            return false;
        }

    }

    public boolean isSalePriceValid(double salePrice) {
        if (isValid.validateNotNegative(salePrice)) {
            return true;
        } else {
            errorMessage += "\nPrice cannot be negative";
            return false;}
    }

    public boolean isQuantityValid(double q) {
        if (isValid.validateNotNegative(q)) {
            return true;
        } else {
            errorMessage += "\nQuantity is not valid";
            return false;
        }
    }

    //function to verify that the chosen vendor ID does not already carry this item
    public boolean noDuplicateItems(List<Map<Integer, String>> items)
    {
        for(int i = 0; i <items.size();i++)
        {
            if(items.get(i).containsKey(vendorID))
            {
                if(items.get(i).get(vendorID) == name)
                {errorMessage += "\nThis item already exists for the chosen vendor";
                    return false;}
            }
        }
        return true;
    }

    public boolean isValid() {
        if (isNameValid(name) && isPurchasePriceValid(purchasePrice) && isSalePriceValid(salePrice)
                && isExpirationValid(expirationDatePicker.getValue()) && isQuantityValid(quantityOnHand)
                && noDuplicateItems(vendorItems)){
            return true;}
        else {
            return false;
        }
    }


//function to update and display errors if they occur
    public void updateErrorLabel(String r) {
        displayError.setText(r);
    }


    //function to store input values from form
    public void saveValues()
    {
        //for vendor Id retrieval
        Map<Integer, String> vendorChoice = vendorIDComboBox.getSelectionModel().getSelectedItem();
        int tempID = (Integer) vendorChoice.keySet().toArray()[0];
        //for conversion to date
        int expirationDay;
        int expirationMonth;
        int expirationYear;
        //add input text to matching fields. Wrapped all numerical entries in try catch blocks
        name = nameTextField.getText();
        vendorID = tempID;
        try{salePrice = Double.parseDouble(salePriceTextField.getText());}
        catch(NumberFormatException e){errorMessage = "Sale Price must be a positive number";
        updateErrorLabel(errorMessage);}
        category = categoryComboBox.getSelectionModel().getSelectedItem();
        expiration.convertToDate(expirationDatePicker);
        try{purchasePrice = Double.parseDouble(purchasePriceTextField.getText());}
        catch(NumberFormatException e){errorMessage = "Purchase Price must be a positive number";
            updateErrorLabel(errorMessage);}
        unit = unitComboBox.getSelectionModel().getSelectedItem();
        try{quantityOnHand = Double.parseDouble(quantityOnHandTextField.getText());}
        catch(NumberFormatException e){errorMessage = "Quantity on hand must be a positive number";
            updateErrorLabel(errorMessage);}

    }

    //members and methods to create vendorID combo box
    // and vendor items list for validation  based on information stored in Database
    private List<Map<Integer, String>> vendors = new ArrayList<>();
    List<Map<String, Object>> DBResults;
    List<Map<String, Object>> DBResults2;
    private List<Map<Integer, String>> vendorItems = new ArrayList<>();

    public void initializeDB() throws SQLException {
        getVendorsFromDB();
        vendorIDComboBox.getItems().addAll(vendors);
    }

    public void getVendorsFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        DBResults2 = connection.getResults("select * from vendor;");
        createVendorItemList(DBResults2);
        connection.closeConnection();

    }

    public void getItemsFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        DBResults = connection.getResults("select * from item;");
        addVendorsToList(DBResults);
        connection.closeConnection();

    }

    public void addVendorsToList(List<Map<String, Object>> results) {
        for (int i = 0; i < results.size(); i++) {
            Map<Integer, String> tempMap = Map.of((Integer) results.get(i).get("ID"),
                    results.get(i).get("company_name").toString());
            vendors.add(tempMap);
        }
    }
    public void createVendorItemList(List<Map<String, Object>> results)
    {
        for (int i = 0; i< results.size();i++)
        {
            Map<Integer, String> tempMap = Map.of((Integer) results.get(i).get("ID"),
                    results.get(i).get("name").toString());
            vendorItems.add(tempMap);
        }
    }

    public void addItemToDB() throws SQLException {
        DBConnection connection = new DBConnection();
        String query = constructQuery();
        connection.addEntryToDB(query);
        connection.closeConnection();
    }

    public String constructQuery() {

        String query = "update item set name = '" + name + "', ID = '"  + "', selling_price='" + salePrice +
                "', category ='" + category.toString() + "', purchase_price='" + purchasePrice +
                "', quantity_available=" + quantityOnHand + ", measurement_unit=" + unit.toString()
                + " expiration= '" + expirationDatePicker.toString() + "vendor_id" + vendorID+ "';";
        return query;
    }
}