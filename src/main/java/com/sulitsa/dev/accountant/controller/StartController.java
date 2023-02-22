package com.sulitsa.dev.accountant.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;

    @FXML
    void initialize() {
        exitBtn.setOnAction(event -> {

            exitBtn.getScene().getWindow().hide();

        });

        startBtn.setOnAction(event -> {

            try {
            startBtn.getScene().getWindow().hide();
            QuestionApplication questionApplication = new QuestionApplication();
            questionApplication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

}
