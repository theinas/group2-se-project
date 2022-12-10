package com.example.se_project;
//@Author Joshua

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.se_project.LoginController.FINAL_ROLE;

public class EditItemViewController {
    private List<String> items = new ArrayList<>();
    private List<Map<Integer, String>> vendors = new ArrayList<>();
    private InputValidator isValid = new InputValidator();
    private String errorMessage;
    @FXML
    protected ComboBox itemsComboBox;
    @FXML
    protected TextField nameTextField;
    @FXML
    protected TextField IDTextField;
    @FXML
    protected TextField salePriceTextField;
    @FXML
    protected ComboBox<Categories> categoriesComboBox;
    @FXML
    protected TextField quantityOnHandTextField;
    @FXML
    protected ComboBox<Measurements> measurementsComboBox;
    @FXML
    protected TextField purchasePriceTextField;
    @FXML
    protected DatePicker expirationDatePicker;
    @FXML
    protected TextField vendorIDTextField;
    @FXML
    protected Button updateItemButton;
    @FXML
    protected Label displayError;
    @FXML
    protected Button backToMainButton;

    public void initialize() throws SQLException {
        getItemsFromDB();
        itemsComboBox.getItems().addAll(items);
    }

    @FXML
    public void onUpdateItemButtonClick() throws SQLException {
        errorMessage="";
        if (isValid()) {
            displayError.setText("Item Updated!");
            updateItemInfo();
        } else {
            displayError.setText(errorMessage);
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


    public void getItemsFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> DBresults1 = connection.getResults("select name from item;");
        List<Map<String, Object>> DBresults2 = connection.getResults("select company_name from vendor;");
        addItemsToList(DBresults1, DBresults2);
        connection.closeConnection();

    }

    public void addItemsToList(List<Map<String, Object>> results1, List<Map<String, Object>> results2) {
        for (int i = 0; i < results1.size(); i++) {
            items.add(results1.get(i).get("name").toString());
        }
        for (int i = 0; i < results2.size(); i++) {
            Map<Integer, String> tempMap = Map.of((Integer) results2.get(i).get("ID"),
                    results2.get(i).get("name").toString());
            vendors.add(tempMap);
        }
    }

    @FXML
    public void onItemComboBoxValueChanged() throws SQLException {
        getSelectedItemInformation();
    }

    public void getSelectedItemInformation() throws SQLException {
        DBConnection connection = new DBConnection();
        List<Map<String, Object>> results = connection.getResults("select * from item where name = '" +
                itemsComboBox.getValue().toString() + "';");
        connection.closeConnection();
        nameTextField.setText(results.get(0).get("name").toString());
        IDTextField.setText(results.get(0).get("ID").toString());
        salePriceTextField.setText(results.get(0).get("selling_price").toString());
        purchasePriceTextField.setText(results.get(0).get("purchase_price").toString());
        categoriesComboBox.setValue((Categories.valueOf(results.get(0).get("category").toString())));
        quantityOnHandTextField.setText(results.get(0).get("quantity_available").toString());
        measurementsComboBox.setValue(Measurements.valueOf(results.get(0).get("measurement_unit").toString()));
        expirationDatePicker.setValue(formatDate(results.get(0).get("expiration").toString()));
        vendorIDTextField.setText(results.get(0).get("vendor_id").toString());


    }

    public LocalDate formatDate(String input)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        return LocalDate.parse(input, formatter);
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
        if (!isValid.validateDate(ex))
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
    public boolean noDuplicateItems(List<Map<Integer, String>> vendors) {
        for (int i = 0; i < vendors.size(); i++) {
            if (vendors.get(i).containsKey(vendorIDTextField.getText())) {
                if (vendors.get(i).get(vendorIDTextField.getText()).equals(nameTextField.getText())) {
                    errorMessage += "\nThis item already exists for the chosen vendor";
                    return false;
                }
            }
        }
        for (int i = 0; i < vendors.size(); i++) {
            if (vendors.get(i).containsKey(vendorIDTextField.getText()))
                return true;
        }
            return false;
    }

    public boolean isValid() {
        if (isNameValid(nameTextField.getText()) && isPurchasePriceValid(Double.parseDouble(purchasePriceTextField.getText()))
                && isSalePriceValid(Double.parseDouble(salePriceTextField.getText())) && isExpirationValid(expirationDatePicker.getValue())
                && isQuantityValid(Double.parseDouble(quantityOnHandTextField.getText())) && noDuplicateItems(vendors)){
            return true;}
        else {
            return false;
        }
    }

    public void updateItemInfo() throws SQLException {
        DBConnection connection = new DBConnection();
        String query = constructQuery();
        connection.addEntryToDB(query);
        connection.closeConnection();
    }

    public String constructQuery() {

            String query = "update item set name = '" + nameTextField.getText() + "', ID = '" + Integer.parseInt(IDTextField.getText()) +
                    "', selling_price='" + Double.parseDouble(salePriceTextField.getText()) + "', category ='" + categoriesComboBox.getValue().toString()
                    + "', purchase_price='" + Double.parseDouble(purchasePriceTextField.getText()) + "', quantity_available=" +
                    Double.parseDouble(quantityOnHandTextField.getText()) + ", measurement_unit=" + measurementsComboBox.getValue().toString()
                    + " expiration= '" + expirationDatePicker.toString() + "vendor_id" + Integer.parseInt(vendorIDTextField.getText())+"';";
            return query;
    }
}
