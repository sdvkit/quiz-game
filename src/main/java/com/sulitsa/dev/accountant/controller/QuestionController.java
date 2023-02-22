package com.sulitsa.dev.accountant.controller;

import java.io.FileInputStream;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.model.Stage;
import com.sulitsa.dev.accountant.service.DataReaderService;
import com.sulitsa.dev.accountant.service.QuestionGeneratorService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;

public class QuestionController {

    @FXML
    private Button answerCBtn, answerDBtn, answerBBtn, answerABtn;
    @FXML
    private ImageView fifty, image, another, all, ring;
    @FXML
    private Label totalCash, questionNumber, questionLabel;
    private int currentStageIndex = 0;
    private int replaceCount = 1;
    private int fiftyChanceCount = 1;
    private int totalMoney = 500;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DataReaderService dataReaderService = new DataReaderService(objectMapper);
    private final List<Stage> stages = new QuestionGeneratorService(dataReaderService).generateQuestions();
    private final Timer timer = new Timer();


    @SneakyThrows
    @FXML
    void initialize() {
        image.setImage(new Image(new FileInputStream("src/main/resources/images/background.png")));
        ring.setImage(new Image(new FileInputStream("src/main/resources/images/telephone-call.png")));
        all.setImage(new Image(new FileInputStream("src/main/resources/images/awareness.png")));
        fifty.setImage(new Image(new FileInputStream("src/main/resources/images/fifty.png")));
        another.setImage(new Image(new FileInputStream("src/main/resources/images/next-button.png")));

        setInfo();

        answerABtn.setOnAction(event -> checkAnswer(Answer.A, stages.get(currentStageIndex), this.answerABtn));
        answerBBtn.setOnAction(event -> checkAnswer(Answer.B, stages.get(currentStageIndex), this.answerBBtn));
        answerCBtn.setOnAction(event -> checkAnswer(Answer.C, stages.get(currentStageIndex), this.answerCBtn));
        answerDBtn.setOnAction(event -> checkAnswer(Answer.D, stages.get(currentStageIndex), this.answerDBtn));

        another.setOnMouseClicked(event -> {

            if(replaceCount == 1) {
                stages.get(currentStageIndex).setId(stages.get(15).getId());
                stages.get(currentStageIndex).setQuestion(stages.get(15).getQuestion());
                stages.get(currentStageIndex).setCorrectAnswer(stages.get(15).getCorrectAnswer());
                stages.get(currentStageIndex).setAnswerVariants(stages.get(15).getAnswerVariants());
                --replaceCount;
                setInfo();
            }

        });
        fifty.setOnMouseClicked(event -> {
            if(fiftyChanceCount == 1) {
                List<Answer> incorrectAnswers = new ArrayList<>(List.of(Answer.A, Answer.B, Answer.C, Answer.D));
                incorrectAnswers.removeIf(answer -> answer == stages.get(currentStageIndex).getCorrectAnswer());
                incorrectAnswers.remove(new Random().nextInt(2));
                hideIncorrectAnswers(incorrectAnswers);
                --fiftyChanceCount;
            }
        });
    }

    @SneakyThrows
    private void checkAnswer(Answer answer, Stage stage, Button button) {

        if(answer == stage.getCorrectAnswer()) {

            if(currentStageIndex < 14){
                ++currentStageIndex;
                button.setStyle("-fx-text-fill: green;");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            questionNumber.setText("Вопрос: " + (currentStageIndex + 1) + "/15");
                            totalMoney += 1000;
                            setInfo();
                            button.setStyle("-fx-text-fill: white;");
                        });
                    }
                }, 2500L);
            }
            else {
                System.out.println("You Won");
            }

        }
        else {
            System.out.println("You lost");
            button.setStyle("-fx-text-fill: red;");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> button.getScene().getWindow().hide());
                }
            }, 2500L);
        }
    }

    private void setInfo(){
        answerABtn.setStyle("-fx-text-fill: white");
        answerBBtn.setStyle("-fx-text-fill: white");
        answerCBtn.setStyle("-fx-text-fill: white");
        answerDBtn.setStyle("-fx-text-fill: white");

        answerABtn.setDisable(false);
        answerBBtn.setDisable(false);
        answerCBtn.setDisable(false);
        answerDBtn.setDisable(false);

        totalCash.setText(String.valueOf(totalMoney));
        questionLabel.setText(stages.get(currentStageIndex).getQuestion());

        String[] answerVariants = stages.get(currentStageIndex).getAnswerVariants();
        answerABtn.setText(answerVariants[0]);
        answerBBtn.setText(answerVariants[1]);
        answerCBtn.setText(answerVariants[2]);
        answerDBtn.setText(answerVariants[3]);
    }

    private void hideIncorrectAnswers(List<Answer> incorrectAnswers){
        for(Answer answer : incorrectAnswers){
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
