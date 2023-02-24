package com.sulitsa.dev.accountant.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.sulitsa.dev.accountant.view.GameRuleApplication;
import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class StartController {

    @FXML
    private Button exitBtn, rulesBtn, startBtn;
    @FXML
    private ImageView exitButtonImg, ruleButtonImg, startButtonImg;

    @SneakyThrows
    @FXML
    void initialize() {
        exitButtonImg.setImage(new Image(new FileInputStream("src/main/resources/images/menu_button.png")));
        ruleButtonImg.setImage(new Image(new FileInputStream("src/main/resources/images/menu_button.png")));
        startButtonImg.setImage(new Image(new FileInputStream("src/main/resources/images/menu_button.png")));

        exitBtn.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        startBtn.setOnAction(event -> {
            try {
                startBtn.getScene().getWindow().hide();
                final QuestionApplication questionApplication = new QuestionApplication();
                questionApplication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        rulesBtn.setOnAction(event -> {
            try {
                rulesBtn.getScene().getWindow().hide();
                final GameRuleApplication gameRuleApplication = new GameRuleApplication();
                gameRuleApplication.start(new javafx.stage.Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
