package com.sulitsa.dev.accountant.controller;

import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.util.ApplicationContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CallFriendController {

    @FXML
    private ImageView phoneImg;
    @FXML
    private Label resultLabel;
    @FXML
    private Button exitBtn;
    private final Timer timer = new Timer();
    private final int randomVariant = new Random().nextInt(3);
    private final int finalVariant = new Random().nextInt(2);

    @SneakyThrows
    @FXML
    void initialize() {
        phoneImg.setImage(new Image(new FileInputStream("src/main/resources/images/IPhone_4S.png")));

        final List<Answer> randomAnswers = new ArrayList<>(Answer.getAll());
        randomAnswers.removeIf(answer -> answer == ApplicationContext.getCurrentStage().getCorrectAnswer());
        final List<Answer> finalAnswer = new ArrayList<>(List.of(randomAnswers.get(randomVariant),ApplicationContext.getCurrentStage().getCorrectAnswer()));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> resultLabel.setText("Ваш друг думает что ответ:\n"+finalAnswer.get(finalVariant)));
            }
        }, 8500L);

        exitBtn.setOnAction(event -> exitBtn.getScene().getWindow().hide());

    }

}
