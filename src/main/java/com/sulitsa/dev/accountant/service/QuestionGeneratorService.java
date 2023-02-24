package com.sulitsa.dev.accountant.service;

import com.sulitsa.dev.accountant.model.Stage;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class QuestionGeneratorService {
    private final DataReaderService dataReaderService;
    private final Random random = new Random();
    private final int[] questionArray = new int[16];

    public List<Stage> generateQuestions() {
        final List<Stage> questions = dataReaderService.readFromFile(new File("src/main/resources/stages.json"));
        for (int i = 0; i < questionArray.length; i++) {
            int generatedQuestionIndex;
            boolean flag;
            do {
                generatedQuestionIndex = random.nextInt(99);
                flag = true;
                for (int j = 0; j < i; j++) {

                    if (questionArray[j] == generatedQuestionIndex) {
                        flag = false;
                        break;
                    }
                }
            } while (!flag);
            questionArray[i] = generatedQuestionIndex;
        }

        final List<Stage> randomQuestions = new ArrayList<>();
        for (int questionIndex : questionArray) {
            for(int i = 0; i < questions.size(); i++){

                if(questionIndex == i){
                    randomQuestions.add(questions.get(questionIndex));
                }

            }
        }

        return randomQuestions;
    }
}

