package com.sulitsa.dev.accountant.controller;

import com.sulitsa.dev.accountant.model.Answer;
import com.sulitsa.dev.accountant.util.ApplicationContext;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommunityHelpController {

    @FXML
    private BarChart<String, Float> communityBarChart;
    private final Random random = new Random();

    @FXML
    void initialize() {
        communityBarChart.setTitle("Решение зала:");

        int totalPercent = 100;
        int variant1 = random.nextInt(totalPercent - 3) + 1;
        int variant2 = random.nextInt(totalPercent - variant1 - 2) + 1;
        int variant3 = random.nextInt(totalPercent - variant1 - variant2 - 1) + 1;
        int variant4 = totalPercent - variant1 - variant2 - variant3;

        List<Integer> answerVariants = new ArrayList<>(List.of(variant1, variant2, variant3, variant4));
        int indexOfCorrectAnswer = Answer.getAll().indexOf(ApplicationContext.getCurrentStage().getCorrectAnswer());
        int maxPercent = answerVariants.stream().max(Integer::compareTo).get();

        if (answerVariants.indexOf(maxPercent) != indexOfCorrectAnswer) {
            Collections.swap(answerVariants, indexOfCorrectAnswer, answerVariants.indexOf(maxPercent));
        }

        XYChart.Series<String,Float> series = new XYChart.Series<>();
        for (int i = 0; i < 4; i++) {
            Answer answer = Answer.getAll().get(i);
            series.getData().add(new XYChart.Data(answer.toString(), answerVariants.get(i)));
        }

        communityBarChart.getData().add(series);
    }
}
