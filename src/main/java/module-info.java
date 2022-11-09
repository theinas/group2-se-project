module com.example.se_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.se_project to javafx.fxml;
    exports com.example.se_project;
}