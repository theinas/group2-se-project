package com.example.se_project;

import javafx.scene.control.Alert;

/*
Developer: Tanni Dev
this class is used to control alert system. we use alert in every class. all alerts are called from here
 */
public class AlertController extends Alert {
    public AlertController(AlertType alertType, String title, String content) {
        super(alertType);
        setTitle(title);
        setHeaderText(null);
        setContentText(content);
        showAndWait();
    }

}
