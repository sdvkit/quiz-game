package com.sulitsa.dev.accountant.controller;

import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.util.ApplicationContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class CallFriendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView phoneImg;
    @FXML
    private Label resultLabel;
    @FXML
    private Button exitBtn;

    private final Timer timer = new Timer();
    private final int randomVariant = new Random().nextInt(3);
    private final int finalVariant = new Random().nextInt(2);

    @FXML
    void initialize() throws FileNotFoundException {

        phoneImg.setImage(new Image(new FileInputStream("src/main/resources/images/IPhone_4S.png")));

        List<Answer> randomAnswers = new ArrayList<>(Answer.getAll());
        randomAnswers.removeIf(answer -> answer == ApplicationContext.getCurrentStage().getCorrectAnswer());
        List<Answer> finalAnswer = new ArrayList<>(List.of(randomAnswers.get(randomVariant),ApplicationContext.getCurrentStage().getCorrectAnswer()));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    resultLabel.setText("Ваш друг думает что ответ:\n"+finalAnswer.get(finalVariant));
                });
            }
        }, 2500L);

        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
        });

    }

}
