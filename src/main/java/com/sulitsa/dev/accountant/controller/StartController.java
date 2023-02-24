package com.sulitsa.dev.accountant.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sulitsa.dev.accountant.view.QuestionApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;
    @FXML
    private ImageView exitButtonImg, ruleButtonImg, startButtonImg;

    @FXML
    void initialize() throws FileNotFoundException {
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
                QuestionApplication questionApplication = new QuestionApplication();
                questionApplication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
