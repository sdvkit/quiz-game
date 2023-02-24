package com.sulitsa.dev.accountant.util;

import com.sulitsa.dev.accountant.model.Stage;

public class ApplicationContext {

    private ApplicationContext() {}

    private static Stage currentStage;
    private static Integer totalMoney;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        ApplicationContext.currentStage = currentStage;
    }

    public static Integer getTotalMoney() {
        return totalMoney;
    }

    public static void setTotalMoney(Integer totalMoney) {
        ApplicationContext.totalMoney = totalMoney;
    }
}
