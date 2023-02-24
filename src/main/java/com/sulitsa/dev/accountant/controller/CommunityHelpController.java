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

        final int totalPercent = 100;
        final int variant1 = random.nextInt(totalPercent - 3) + 1;
        final int variant2 = random.nextInt(totalPercent - variant1 - 2) + 1;
        final int variant3 = random.nextInt(totalPercent - variant1 - variant2 - 1) + 1;
        final int variant4 = totalPercent - variant1 - variant2 - variant3;

        final List<Integer> answerVariants = new ArrayList<>(List.of(variant1, variant2, variant3, variant4));
        final int indexOfCorrectAnswer = Answer.getAll().indexOf(ApplicationContext.getCurrentStage().getCorrectAnswer());
        final int maxPercent = answerVariants.stream().max(Integer::compareTo).get();

        if (answerVariants.indexOf(maxPercent) != indexOfCorrectAnswer) {
            Collections.swap(answerVariants, indexOfCorrectAnswer, answerVariants.indexOf(maxPercent));
        }

        final XYChart.Series<String,Float> series = new XYChart.Series<>();
        for (int i = 0; i < 4; i++) {
            final Answer answer = Answer.getAll().get(i);
            series.getData().add(new XYChart.Data(answer.toString(), answerVariants.get(i)));
        }

        communityBarChart.getData().add(series);
    }
}
