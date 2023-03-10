package com.sulitsa.dev.accountant.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.model.Stage;
import com.sulitsa.dev.accountant.service.DataReaderService;
import com.sulitsa.dev.accountant.service.QuestionGeneratorService;
import com.sulitsa.dev.accountant.util.ApplicationContext;
import com.sulitsa.dev.accountant.view.CallFriendApplication;
import com.sulitsa.dev.accountant.view.CommunityHelpApplication;
import com.sulitsa.dev.accountant.view.EndGameApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import lombok.SneakyThrows;

public class QuestionController {

    @FXML
    private Button answerCBtn, answerDBtn, answerBBtn, answerABtn;
    @FXML
    private ImageView fiftyChanceImg, backgroundImg, anotherQuestionImg, communityHelpImg, callFriendImg;
    @FXML
    private Label totalCashLabel, questionNumberLabel, questionLabel;
    @FXML
    private MediaView backSoundPlayer, wonSoundPlayer, lostSoundPlayer, callingSoundPlayer, communitySoundPlayer, replaceSoundPlayer;
    private Integer currentStageIndex = 0;
    private Boolean replaceChance = true;
    private Boolean fiftyChance = true;
    private Boolean communityHelp = true;
    private Boolean callFriend = true;
    private Integer totalMoney = 0;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DataReaderService dataReaderService = new DataReaderService(objectMapper);
    private final List<Stage> stages = new QuestionGeneratorService(dataReaderService).generateQuestions();
    private final Timer timer = new Timer();

    @SneakyThrows
    @FXML
    void initialize() {
        backSoundPlayer.setMediaPlayer(new MediaPlayer(new Media(getClass().getResource("/sound/back_sound.mp3").toURI().toString())));
        wonSoundPlayer.setMediaPlayer(new MediaPlayer(new Media(getClass().getResource("/sound/guess_sound.mp3").toURI().toString())));
        lostSoundPlayer.setMediaPlayer(new MediaPlayer(new Media(getClass().getResource("/sound/fail_sound.mp3").toURI().toString())));
        callingSoundPlayer.setMediaPlayer(new MediaPlayer(new Media(getClass().getResource("/sound/calling_sound.mp3").toURI().toString())));
        communitySoundPlayer.setMediaPlayer(new MediaPlayer(new Media(getClass().getResource("/sound/discussion_sound.mp3").toURI().toString())));
        replaceSoundPlayer.setMediaPlayer(new MediaPlayer(new Media(getClass().getResource("/sound/replace_question_sound.mp3").toURI().toString())));

        backSoundPlayer.getMediaPlayer().setCycleCount(100);
        backSoundPlayer.getMediaPlayer().play();

        backgroundImg.setImage(new Image(new FileInputStream("src/main/resources/images/background.png")));
        callFriendImg.setImage(new Image(new FileInputStream("src/main/resources/images/telephone-call.png")));
        communityHelpImg.setImage(new Image(new FileInputStream("src/main/resources/images/awareness.png")));
        fiftyChanceImg.setImage(new Image(new FileInputStream("src/main/resources/images/fifty.png")));
        anotherQuestionImg.setImage(new Image(new FileInputStream("src/main/resources/images/next-button.png")));

        setApplicationStyle();

        answerABtn.setOnAction(event -> checkAnswer(Answer.A, stages.get(currentStageIndex), answerABtn));
        answerBBtn.setOnAction(event -> checkAnswer(Answer.B, stages.get(currentStageIndex), answerBBtn));
        answerCBtn.setOnAction(event -> checkAnswer(Answer.C, stages.get(currentStageIndex), answerCBtn));
        answerDBtn.setOnAction(event -> checkAnswer(Answer.D, stages.get(currentStageIndex), answerDBtn));

        anotherQuestionImg.setOnMouseClicked(event -> {

            if(replaceChance) {
                replaceSoundPlayer.getMediaPlayer().stop();
                replaceSoundPlayer.getMediaPlayer().play();
                stages.get(currentStageIndex).setId(stages.get(15).getId());
                stages.get(currentStageIndex).setQuestion(stages.get(15).getQuestion());
                stages.get(currentStageIndex).setCorrectAnswer(stages.get(15).getCorrectAnswer());
                stages.get(currentStageIndex).setAnswerVariants(stages.get(15).getAnswerVariants());
                replaceChance = false;
                setApplicationStyle();

                try {
                    anotherQuestionImg.setImage(new Image(new FileInputStream("src/main/resources/images/used-skip.png")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        });

        fiftyChanceImg.setOnMouseClicked(event -> {

            if(fiftyChance) {
                try{
                    replaceSoundPlayer.getMediaPlayer().stop();
                    replaceSoundPlayer.getMediaPlayer().play();
                    final List<Answer> incorrectAnswers = new ArrayList<>(List.of(Answer.A, Answer.B, Answer.C, Answer.D));
                    incorrectAnswers.removeIf(answer -> answer == stages.get(currentStageIndex).getCorrectAnswer());
                    incorrectAnswers.remove(new Random().nextInt(2));
                    hideIncorrectAnswers(incorrectAnswers);
                    fiftyChance = false;
                    fiftyChanceImg.setImage(new Image(new FileInputStream("src/main/resources/images/used-fifty.png")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        communityHelpImg.setOnMouseClicked(event -> {

            if (communityHelp) {
                try {
                    communitySoundPlayer.getMediaPlayer().play();
                    communitySoundPlayer.getMediaPlayer().setStopTime(Duration.millis(3000));

                    ApplicationContext.setCurrentStage(stages.get(currentStageIndex));
                    final CommunityHelpApplication communityHelpApplication = new CommunityHelpApplication();
                    communityHelpApplication.start(new javafx.stage.Stage());
                    communityHelp = false;
                    communityHelpImg.setImage(new Image(new FileInputStream("src/main/resources/images/used-awareness.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        callFriendImg.setOnMouseClicked(event -> {

            if(callFriend) {

                try {
                    callingSoundPlayer.getMediaPlayer().setStopTime(Duration.millis(8000));
                    callingSoundPlayer.getMediaPlayer().play();

                    ApplicationContext.setCurrentStage(stages.get(currentStageIndex));
                    callFriendImg.setImage(new Image(new FileInputStream("src/main/resources/images/used-telephone.png")));
                    final CallFriendApplication callFriendApplication = new CallFriendApplication();
                    callFriendApplication.start(new javafx.stage.Stage());
                    callFriend = false;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private void checkAnswer(Answer answer, Stage stage, Button button) {
        setDisableButtons();

        if(answer == stage.getCorrectAnswer()) {
            wonSoundPlayer.getMediaPlayer().play();
            checkQuestionIndex(button);
        } else {
            lostSoundPlayer.getMediaPlayer().play();
            backSoundPlayer.getMediaPlayer().stop();
            endGame(button);
        }

    }

    @SneakyThrows
    private void checkQuestionIndex(Button button) {

        if(currentStageIndex < 14){
            ++currentStageIndex;
            button.setStyle("-fx-text-fill: green;");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        questionNumberLabel.setText("????????????: " + (currentStageIndex + 1) + "/15");
                        totalMoney += 1000;
                        setApplicationStyle();
                        button.setStyle("-fx-text-fill: white;");
                    });
                }
            }, 2500L);
        } else {
            totalMoney += 1000;
            ApplicationContext.setTotalMoney(totalMoney);
            final EndGameApplication endGameApplication = new EndGameApplication();
            endGameApplication.start(new javafx.stage.Stage());
            totalCashLabel.getScene().getWindow().hide();
        }

    }

    private void endGame(Button button) {
        button.setStyle("-fx-text-fill: red;");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        ApplicationContext.setTotalMoney(totalMoney);
                        final EndGameApplication endGameApplication = new EndGameApplication();
                        endGameApplication.start(new javafx.stage.Stage());
                        totalCashLabel.getScene().getWindow().hide();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        }, 2500L);
    }

    private void setApplicationStyle(){
        wonSoundPlayer.getMediaPlayer().stop();
        lostSoundPlayer.getMediaPlayer().stop();
        callingSoundPlayer.getMediaPlayer().stop();
        communitySoundPlayer.getMediaPlayer().stop();

        answerABtn.setStyle("-fx-text-fill: white");
        answerBBtn.setStyle("-fx-text-fill: white");
        answerCBtn.setStyle("-fx-text-fill: white");
        answerDBtn.setStyle("-fx-text-fill: white");

        answerABtn.setDisable(false);
        answerBBtn.setDisable(false);
        answerCBtn.setDisable(false);
        answerDBtn.setDisable(false);

        totalCashLabel.setText(String.valueOf(totalMoney));
        questionLabel.setText(stages.get(currentStageIndex).getQuestion());

        final String[] answerVariants = stages.get(currentStageIndex).getAnswerVariants();
        answerABtn.setText(answerVariants[0]);
        answerBBtn.setText(answerVariants[1]);
        answerCBtn.setText(answerVariants[2]);
        answerDBtn.setText(answerVariants[3]);
    }

    private void setDisableButtons(){
        answerABtn.setDisable(true);
        answerBBtn.setDisable(true);
        answerCBtn.setDisable(true);
        answerDBtn.setDisable(true);
    }

    private void hideIncorrectAnswers(List<Answer> incorrectAnswers) {
        for(Answer answer : incorrectAnswers) {

            if(answer == Answer.A){
                answerABtn.setDisable(true);
                answerABtn.setStyle("-fx-text-fill: gray");
            }

            if(answer == Answer.B){

                answerBBtn.setDisable(true);
                answerBBtn.setStyle("-fx-text-fill: gray");
            }

            if(answer == Answer.C){
                answerCBtn.setDisable(true);
                answerCBtn.setStyle("-fx-text-fill: gray");
            }

            if(answer == Answer.D){
                answerDBtn.setDisable(true);
                answerDBtn.setStyle("-fx-text-fill: gray");
            }

        }
    }
}
