package com.sulitsa.dev.accountant.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.sulitsa.dev.accountant.util.ApplicationContext;
import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class EndController {

    @FXML
    private Button exitBtn, restartBtn;
    @FXML
    private Label winLabel;
    @FXML
    private ImageView exitButtonImg, restartButtonImg;

    @SneakyThrows
    @FXML
    void initialize() {
        winLabel.setText(String.format("Ваш выигрыш: %d", ApplicationContext.getTotalMoney()));
        exitButtonImg.setImage(new Image(new FileInputStream("src/main/resources/images/menu_button.png")));
        restartButtonImg.setImage(new Image(new FileInputStream("src/main/resources/images/menu_button.png")));

        restartBtn.setOnAction(event -> {
            try {
                winLabel.getScene().getWindow().hide();
                final QuestionApplication questionApplication = new QuestionApplication();
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
