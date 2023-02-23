package com.sulitsa.dev.accountant.util;

import com.sulitsa.dev.accountant.model.Stage;

public class ApplicationContext {

    private ApplicationContext() {}

    private static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        ApplicationContext.currentStage = currentStage;
    }
}
