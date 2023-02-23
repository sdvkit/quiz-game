package com.sulitsa.dev.accountant.controller;

import java.io.IOException;

import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;

    @FXML
    void initialize() {
        exitBtn.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
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
