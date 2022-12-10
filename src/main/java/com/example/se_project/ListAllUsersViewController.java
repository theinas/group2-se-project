//@Author: Robert Tedeschi

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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.se_project.LoginController.FINAL_ROLE;

public class ListAllUsersViewController {
    ResultSet users;
    private ObservableList<ObservableList> data;
    @FXML
    protected TableView usersTableView;
    @FXML
    protected Button backToMainButton;

    @FXML
    public void onBackToMainButtonClick() throws IOException
    {
        Parent root;
        if(FINAL_ROLE.equals("OWNER")) {
            root = FXMLLoader.load(getClass().getResource("Owner-view.fxml"));
        }
        else{
            root = FXMLLoader.load(getClass().getResource("Admin-view.fxml"));
        }
        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void initialize() throws SQLException {
        getUsersFromDB();
        addResultsToTable();
    }

    public void getUsersFromDB() throws SQLException {
        DBConnection connection = new DBConnection();
        users = connection.getResultSet(getQuery());
    }
    public String getQuery()
    {
        return "select * from users;";
    }
    public void addResultsToTable() throws SQLException {
        data = FXCollections.observableArrayList();
        for(int i=0 ; i<users.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(users.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
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

            usersTableView.getColumns().addAll(col);
        }
        addToObservableList();
        addToTableView();
    }
    public void addToObservableList() throws SQLException {
        while(users.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=users.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(users.getString(i));
            }
            data.add(row);

        }
    }
    public void addToTableView()
    {
        usersTableView.setItems(data);
    }

}
