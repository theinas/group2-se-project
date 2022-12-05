package com.example.se_project;

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

public class ListAllCustomersViewController {
    ResultSet customers;
    private ObservableList<ObservableList> data;
    @FXML
    protected TableView customersTableView;
    @FXML
    protected Button backToMainButton;

    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void initialize() throws SQLException {
        getCustomersFromDB();
        addResultsToTable();
    }

    public void getCustomersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        customers = connection.getResultSet(getQuery());
    }
    public String getQuery()
    {
        return "select * from customer;";
    }
    public void addResultsToTable() throws SQLException {
        data = FXCollections.observableArrayList();
        for(int i=0 ; i<customers.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(customers.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    if (param.getValue().get(j)!= null)
                    {
                        return new SimpleStringProperty(param.getValue().get(j).toString());

                    }
                    else
                    {
                        return new SimpleStringProperty("");
                    }
                }
            });

            customersTableView.getColumns().addAll(col);
        }
        addToObservableList();
        addToTableView();
    }
    public void addToObservableList() throws SQLException {
        while(customers.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=customers.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(customers.getString(i));
            }
            data.add(row);

        }
    }
    public void addToTableView()
    {
        customersTableView.setItems(data);
    }

}
