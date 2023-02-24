package com.sulitsa.dev.accountant.controller;

import java.io.FileInputStream;
import java.io.IOException;

import com.sulitsa.dev.accountant.view.StartGameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class GameRuleController {
    @FXML
    private Button exitBtn;
    @FXML
    private ImageView fiftyChanceImg, anotherQuestionImg, communityHelpImg, callFriendImg, exitButtonImg;

    @SneakyThrows
    @FXML
    void initialize() {
        callFriendImg.setImage(new Image(new FileInputStream("src/main/resources/images/telephone-call.png")));
        communityHelpImg.setImage(new Image(new FileInputStream("src/main/resources/images/awareness.png")));
        fiftyChanceImg.setImage(new Image(new FileInputStream("src/main/resources/images/fifty.png")));
        anotherQuestionImg.setImage(new Image(new FileInputStream("src/main/resources/images/next-button.png")));
        exitButtonImg.setImage(new Image(new FileInputStream("src/main/resources/images/menu_button.png")));

        exitBtn.setOnAction(event -> {
            try {
                exitBtn.getScene().getWindow().hide();
                final StartGameApplication startGameApplication = new StartGameApplication();
                startGameApplication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
