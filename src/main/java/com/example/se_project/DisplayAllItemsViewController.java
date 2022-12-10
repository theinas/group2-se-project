package com.example.se_project;
// Joshua

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

import javafx.scene.control.TreeTableColumn;

import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.se_project.LoginController.FINAL_ROLE;

public class DisplayAllItemsViewController {
    ResultSet items;
    private ObservableList<ObservableList> DBresults;
    @FXML
    protected TableView itemsTableView;
    @FXML
    protected Button backToMainButton;

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
            window.setScene(new Scene(root));}
        else if (FINAL_ROLE.equals("SALES_PERSON")) {
            Parent root = FXMLLoader.load(getClass().getResource("Sales-person-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));}
       /* else if (FINAL_ROLE.equals("ACCOUNTANT")) {
            Parent root = FXMLLoader.load(getClass().getResource("Accountant-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));}*/
        /* else if (FINAL_ROLE.equals("ADMIN")) {
            Parent root = FXMLLoader.load(getClass().getResource("Admin-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));}*/
        else {
            Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
            Stage window = (Stage) backToMainButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    public void initialize() throws SQLException {
        getItemsFromDB();
        addResultsToTable();
    }

    public void getItemsFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        items = connection.getResultSet(Query());
    }

    public void addResultsToTable() throws SQLException {
        DBresults = FXCollections.observableArrayList();
        for(int i=0 ; i<items.getMetaData().getColumnCount(); i++){
            final int j = i;
            TableColumn column = new TableColumn(items.getMetaData().getColumnName(i+1));
            column.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> parameter) {
                    if (parameter.getValue().get(j)!= null)
                    {
                        return new SimpleStringProperty(parameter.getValue().get(j).toString());

                    }
                    else
                    {
                        return new SimpleStringProperty("");
                    }
                }
            });

            itemsTableView.getColumns().addAll(column);
        }
        addToObservableList();
        addToTableView();
    }
    public void addToObservableList() throws SQLException {
        while(items.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=items.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(items.getString(i));
            }
            DBresults.add(row);

        }
    }

    public String Query()
    {
        return "select * from item;";
    }
    public void addToTableView()
    {
        itemsTableView.setItems(DBresults);
    }

}
