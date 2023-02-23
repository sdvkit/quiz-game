package com.sulitsa.dev.accountant.controller;

import java.io.IOException;

import com.sulitsa.dev.accountant.view.EndGameApplication;
import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndController {

    @FXML
    private Button exitBtn;

    @FXML
    private Button restartBtn;

    @FXML
    private Label winLabel;

    @FXML
    void initialize() {
        winLabel.setText(String.format("Ваш выигрыш: %d", EndGameApplication.getTotalMoney()));

        restartBtn.setOnAction(event -> {
            try {
                winLabel.getScene().getWindow().hide();
                QuestionApplication questionApplication = new QuestionApplication();
                questionApplication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        exitBtn.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
