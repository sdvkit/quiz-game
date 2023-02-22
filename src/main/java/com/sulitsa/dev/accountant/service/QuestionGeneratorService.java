package com.sulitsa.dev.accountant.service;


import com.sulitsa.dev.accountant.model.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGeneratorService {
    Random random = new Random();
    int[] arr = new int[16];
    int temp;
    boolean flag;
    public List<Stage> GenerateQuestion(List<Stage> questions) {
        for (int i = 0; i < arr.length; i++) {
            do {
                temp = random.nextInt(99);
                flag = true;
                for (int j = 0; j < i; j++) {
                    if (arr[j] == temp) {
                        flag = false;
                        break;
                    }
                }
            } while (!flag);
            arr[i] = temp;
        }
        List<Stage> randomQuestions = new ArrayList<>();
        for (int i : arr) {
            for(int j = 0; j < questions.size(); j++){
                if(i == j){
                    randomQuestions.add(questions.get(i));
                }
            }
        }
        return randomQuestions;
    }
}

