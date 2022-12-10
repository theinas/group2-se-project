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
import java.util.List;
import java.util.Map;

public class SearchForItemViewController {
    private ToggleGroup toggle = new ToggleGroup();
    private List<Map<String, Object>> DBresults;

    @FXML
    protected RadioButton byIdRadioButton;
    @FXML
    protected RadioButton byNameRadioButton;
    @FXML
    protected RadioButton byExpirationRadioButton;
    @FXML
    protected TextField searchTextField;
    @FXML
    protected Button searchButton;
    @FXML
    protected Button fullItemListButton;
    @FXML
    protected Label idLabel;
    @FXML
    protected Label idValueLabel;
    @FXML
    protected Label nameLabel;
    @FXML
    protected Label nameValueLabel;
    @FXML
    protected Label quantityLabel;
    @FXML
    protected Label quantityValueLabel;
    @FXML
    protected Label measurementValueLabel;
    @FXML
    protected Label salePriceLabel;
    @FXML
    protected Label salePriceValueLabel;
    @FXML
    protected Label purchasePriceLabel;
    @FXML
    protected Label purchasePriceValueLabel;
    @FXML
    protected Label expirationLabel;
    @FXML
    protected Label expirationValueLabel;
    @FXML
    protected Label vendorLabel;
    @FXML
    protected Label vendorValueLabel;
    @FXML
    protected Button backToMainButton;
    @FXML
    protected Label errorLabel;
    protected String errorMessage;


    public void initialize()
    {
        byIdRadioButton.setToggleGroup(toggle);
        byNameRadioButton.setToggleGroup(toggle);
        byExpirationRadioButton.setToggleGroup(toggle);
    }
    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        if(User.checkRole().equals("OWNER")){
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));}

        else if (User.checkRole().equals("INVENTORY_MANAGER")){
        Parent root = FXMLLoader.load(getClass().getResource("InventoryManager-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));}

        else if (User.checkRole().equals("PURCHASER")){
        Parent root = FXMLLoader.load(getClass().getResource("Purchaser-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));}
        else
        {Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));}
    }
    @FXML
    public void onDisplayAllItemsButtonClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Display-all-items-view.fxml"));
        Stage window = (Stage) fullItemListButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    public void onSearchButtonClick() throws SQLException {

        emptyLabels();

        if(isFilterSelected())
        {
            findItemInDB();
            if(!foundInDB())
            {
                notFoundAlert();
                searchTextField.setText("");
            }
            else
            {
                showItemInfo();
            }
        }
        else
        {
            noFilterAlert();
        }

    }
    public boolean foundInDB()
    {
        if (DBresults.isEmpty())
            return false;
        else
            return true;
    }
    public void findItemInDB() throws SQLException
    {
        DBConnection connection = new DBConnection();
        DBresults =  connection.getResults(Query());
        connection.closeConnection();

    }
    public void displayResults() throws SQLException {

    }
    public String Query()
    {
        if (byIdRadioButton.isSelected()) {
            return "select * from item where ID =" + Integer.parseInt(searchTextField.getText()) + ";";
        }
        else if (byNameRadioButton.isSelected())
        {
            return "select * from item where name ='"+ searchTextField.getText()+ "';";
        }
        else
        {
            int month = 0, day = 0, year = 0;

            try{
            String input = searchTextField.getText();
            month = Integer.parseInt(input.substring(0,1));
            day = Integer.parseInt(input.substring(3,4));
            year = Integer.parseInt(input.substring(6,9));}
            catch(NumberFormatException e){incorrectDateAlert();}
            Date query = new Date(day, month, year);
            return "select * from item where name = '" + query +"';";

        }
    }
    public void notFoundAlert()
    {
        Alert notFound = new Alert(Alert.AlertType.ERROR, "Item not found! try again", ButtonType.CLOSE);
        notFound.show();

    }
    public void noFilterAlert()
    {
        Alert noFilter = new Alert(Alert.AlertType.WARNING, "No search by option selected. Select then try again", ButtonType.OK);
        noFilter.show();
    }

    public boolean isFilterSelected()
    {
        if (byNameRadioButton.isSelected() || byIdRadioButton.isSelected())
            return true;
        else
            return false;
    }
    //alert to inform user date was entered in incorrect format
    public void incorrectDateAlert()
    {
        Alert badDate = new Alert(Alert.AlertType.WARNING, "Date entered incorrectly. Please use MM/DD/YYYY format.", ButtonType.OK);
        badDate.show();
    }

    public void showItemInfo()
    {
        idLabel.setText("Item ID: ");
        idValueLabel.setText(DBresults.get(0).get("ID").toString());

        nameLabel.setText("Item Name: ");
        nameValueLabel.setText(DBresults.get(0).get("name").toString());

        quantityLabel.setText("Quantity on Hand: ");
        quantityValueLabel.setText(DBresults.get(0).get("quantity_available").toString());

        measurementValueLabel.setText(DBresults.get(0).get("measurement_unit").toString());

        salePriceLabel.setText("Sale Price: ");
        salePriceValueLabel.setText("$" + DBresults.get(0).get("selling_price").toString());

        purchasePriceLabel.setText("Purchase Price: ");
        purchasePriceValueLabel.setText("$" + DBresults.get(0).get("purchase_price").toString());

        expirationLabel.setText("Expiration Date: ");
        expirationValueLabel.setText(DBresults.get(0).get("expiration").toString());

        vendorLabel.setText("Vendor ID: ");
        vendorValueLabel.setText(DBresults.get(0).get("vendor_id").toString());

    }
    public void emptyLabels()
    {
        idLabel.setText("");
        idValueLabel.setText("");

        nameLabel.setText("");
        nameValueLabel.setText("");

        quantityLabel.setText("");
        quantityValueLabel.setText("");

        salePriceLabel.setText("");
        salePriceValueLabel.setText("");

        purchasePriceLabel.setText("");
        purchasePriceValueLabel.setText("");

        measurementValueLabel.setText("");

        expirationLabel.setText("");
        expirationValueLabel.setText("");

        vendorLabel.setText("");
        vendorValueLabel.setText("");
    }
}
