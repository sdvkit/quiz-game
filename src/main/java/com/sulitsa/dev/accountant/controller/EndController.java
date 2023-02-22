package com.sulitsa.dev.accountant.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sulitsa.dev.accountant.view.EndGameApplication;
import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.stage.Stage;

public class EndController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

            EndGameApplication.getCurrentStage().close();
            QuestionApplication questionApplication = new QuestionApplication();
            try {
                questionApplication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        exitBtn.setOnAction(event -> {

            Thread.currentThread().interrupt();
            EndGameApplication.getCurrentStage().close();

        });
    }
}
