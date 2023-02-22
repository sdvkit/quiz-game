package com.sulitsa.dev.accountant.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.model.Stage;
import com.sulitsa.dev.accountant.service.DataReaderService;
import com.sulitsa.dev.accountant.service.QuestionGeneratorService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import lombok.SneakyThrows;

public class QuestionController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button answerCBtn,answerDBtn,answerBBtn,answerABtn;
    @FXML
    private ImageView fifty,image,another,all;
    @FXML
    private Label totalCash,questionNumber,questionLabel;
    @FXML
    private ImageView ring;
    private String[] answers;
    private int index = 0;
    private int cash = 500;
    private int replaceCount = 1;
    private List<Stage> stages = new QuestionGeneratorService().
            GenerateQuestion(new DataReaderService(new ObjectMapper()).
                    readFromFile(new File("src/main/resources/stages.json")));
    @FXML
    void initialize() throws FileNotFoundException {
        image.setImage(new Image(new FileInputStream("src/main/resources/images/background.png")));
        ring.setImage(new Image(new FileInputStream("src/main/resources/images/telephone-call.png")));
        all.setImage(new Image(new FileInputStream("src/main/resources/images/awareness.png")));
        fifty.setImage(new Image(new FileInputStream("src/main/resources/images/fifty.png")));
        another.setImage(new Image(new FileInputStream("src/main/resources/images/next-button.png")));
        setInfo();
        answerABtn.setOnAction(e -> {
            checkAnswer(Answer.A, stages.get(index), this.answerABtn);
        });
        answerBBtn.setOnAction(e -> {
            checkAnswer(Answer.B, stages.get(index), this.answerBBtn);
        });
        answerCBtn.setOnAction(e -> {
            checkAnswer(Answer.C, stages.get(index), this.answerCBtn);
        });
        answerDBtn.setOnAction(e -> {
            checkAnswer(Answer.D, stages.get(index), this.answerDBtn);
        });
        another.setOnMouseClicked(e -> {
            if(replaceCount == 1) {
                stages.get(index).setId(stages.get(15).getId());
                stages.get(index).setQuestion(stages.get(15).getQuestion());
                stages.get(index).setCorrectAnswer(stages.get(15).getCorrectAnswer());
                stages.get(index).setAnswerVariants(stages.get(15).getAnswerVariants());
                replaceCount -= 1;
                setInfo();
            }
        });
    }

    @SneakyThrows
    private void checkAnswer(Answer answer, Stage stage, Button button){
        if(answer == stage.getCorrectAnswer()) {
            if(index<14){
                index += 1;
                questionNumber.setText("Вопрос: " + (index + 1) + "/15");
                setInfo();
            }
            else {
                System.out.println("You Won");
            }
        }
        else {
            System.out.println("You lost");
            button.getScene().getWindow().hide();
        }
    }

    private void setInfo(){
        totalCash.setText(String.valueOf(cash));
        questionLabel.setText(stages.get(index).getQuestion());
        answers = stages.get(index).getAnswerVariants();
        answerABtn.setText(answers[0]);
        answerBBtn.setText(answers[1]);
        answerCBtn.setText(answers[2]);
        answerDBtn.setText(answers[3]);
    }

}
