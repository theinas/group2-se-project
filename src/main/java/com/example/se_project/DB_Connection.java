/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.se_project;
/*
this class create database and tables and make connection with database through objects
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DB_Connection {

    public Connection getConnection() {
        DB_Connection obj_DB_Connection = new DB_Connection();
        Connection connection = null;
        connection = obj_DB_Connection.create_connection();
        System.out.println("Connection Established Successfully");
        String q0 = "CREATE DATABASE if not exists distributer;";
        String q1 = "CREATE TABLE if not exists distributer.vendor (id int NOT NULL AUTO_INCREMENT,fname varchar(255) not null,staddress varchar(255) not null,city varchar(755) not null,state varchar(255) not null,phone varchar(255) not null,balance varchar(255) not null,lastpaidamount varchar(255) not null,lastorderdate varchar(255) not null,sessionaldiscountstartdate varchar(255) not null, PRIMARY KEY (id));";
        String q7 = "CREATE TABLE if not exists distributer.customerinvoice (id int NOT NULL AUTO_INCREMENT,customerorderid varchar(255) not null,givencustomername varchar(255) not null,invoicedate varchar(255) not null,orderdate varchar(255),subtotal varchar(255),PRIMARY KEY(id));";


        try {
            Statement s = connection.createStatement();
            s.execute(q0);
            s.execute(q1);


            System.out.println("Successfull");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public Connection create_connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql?serverTimezone=UTC", "root", "");

        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
