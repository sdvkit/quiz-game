package com.sulitsa.dev.accountant.controller;

import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.util.ApplicationContext;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.lang.reflect.Array;
import java.util.*;

public class CommunityHelpController {

    @FXML
    private BarChart<String, Float> communityBarChart;
    private final Random random = new Random();

    @FXML
    void initialize() {
        XYChart.Series<String,Float> series = new XYChart.Series<>();
        communityBarChart.setTitle("Решение зала:");
        int totalPercent = 100;

        int variant1 = random.nextInt(totalPercent - 3) + 1;
        int variant2 = random.nextInt(totalPercent - variant1 - 2) + 1;
        int variant3 = random.nextInt(totalPercent - variant1 - variant2 - 1) + 1;
        int variant4 = totalPercent - variant1 - variant2 - variant3;

        List<Integer> answers = new ArrayList<>(List.of(variant1, variant2, variant3, variant4));
        int indexOfCorrectAnswer = Answer.getAll().indexOf(ApplicationContext.getCurrentStage().getCorrectAnswer());
        System.out.println("BEFORE");
        answers.forEach(System.out::println);
        Collections.swap(answers, indexOfCorrectAnswer, answers.size() - indexOfCorrectAnswer);
        System.out.println("AFTEr");
        answers.forEach(System.out::println);
//        series.getData().add(new XYChart.Data(Answer.A.toString(), num1));

        communityBarChart.getData().add(series);
    }
}
