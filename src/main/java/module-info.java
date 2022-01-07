module com.example.birthdaycakeshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.birthdaycakeshop to javafx.fxml;
    exports com.example.birthdaycakeshop;
}