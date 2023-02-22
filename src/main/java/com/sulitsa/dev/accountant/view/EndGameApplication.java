package com.sulitsa.dev.accountant.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class EndGameApplication extends Application {

    private static Stage currentStage;
    private static Integer totalMoney;

    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(EndGameApplication.class.getResource("end_game_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1089, 670);
        stage.setTitle("Become An Accountant");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public EndGameApplication(Integer money) {
        totalMoney = money;
    }

    public static Integer getTotalMoney() {
        return totalMoney;
    }

    public static void main(String[] args) {
        launch();
    }
}