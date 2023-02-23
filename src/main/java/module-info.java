module com.sulitsa.dev.accountant {

    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires javafx.media;
    requires static lombok;


    opens com.sulitsa.dev.accountant.view to javafx.fxml;
    exports com.sulitsa.dev.accountant.view;

    opens com.sulitsa.dev.accountant.controller to javafx.fxml;
    exports com.sulitsa.dev.accountant.controller;

    opens com.sulitsa.dev.accountant.model to com.fasterxml.jackson.databind;
    exports com.sulitsa.dev.accountant.model;
}