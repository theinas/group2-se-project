package com.example.se_project;

// Developer: Tanni Dev

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class Vendor_CustomerControllerTest {
    /**
     * Method under test: {@link Vendor_CustomerController#initialize(URL, ResourceBundle)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInitialize() {
        // TODO: Complete this test.
        //   Reason: F009 Internal error.
        //   java.lang.AssertionError: Can't invoke method com.apple.laf.resources.aqua() with arguments []
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support/) providing details about this error.

        // Arrange
        // TODO: Populate arranged inputs
        Vendor_CustomerController vendor_CustomerController = null;
        URL url = null;
        ResourceBundle rb = null;

        // Act
        vendor_CustomerController.initialize(url, rb);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link Vendor_CustomerController#deleteItemfromItemTable(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteItemfromItemTable() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.sql.SQLNonTransientConnectionException: Could not create connection to database server.
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:110)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
        //       at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
        //       at com.mysql.cj.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:1008)
        //       at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:825)
        //       at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:455)
        //       at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:240)
        //       at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:207)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:681)
        //       at java.sql.DriverManager.getConnection(DriverManager.java:252)
        //       at com.example.se_project.DBConnection.<init>(DBConnection.java:16)
        //       at com.example.se_project.Vendor_CustomerController.deleteItemfromItemTable(Vendor_CustomerController.java:400)
        //   In order to prevent deleteItemfromItemTable(int)
        //   from throwing SQLNonTransientConnectionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deleteItemfromItemTable(int).
        //   See https://diff.blue/R013 to resolve this issue.

        (new Vendor_CustomerController()).deleteItemfromItemTable(42);
    }

    /**
     * Method under test: {@link Vendor_CustomerController#clear()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testClear() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javafx.scene.control.TextField.clear()" because "this.txtid" is null
        //       at com.example.se_project.Vendor_CustomerController.clear(Vendor_CustomerController.java:406)
        //   In order to prevent clear()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   clear().
        //   See https://diff.blue/R013 to resolve this issue.

        (new Vendor_CustomerController()).clear();
    }

    /**
     * Method under test: {@link Vendor_CustomerController#setDefault()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetDefault() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "javafx.scene.control.TableColumn.setCellValueFactory(javafx.util.Callback)" because "this.cfname" is null
        //       at com.example.se_project.Vendor_CustomerController.setDefault(Vendor_CustomerController.java:462)
        //   In order to prevent setDefault()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   setDefault().
        //   See https://diff.blue/R013 to resolve this issue.

        (new Vendor_CustomerController()).setDefault();
    }
}

