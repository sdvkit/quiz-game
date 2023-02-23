package com.sulitsa.dev.accountant.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EndGameApplication extends Application {

    private static Integer totalMoney;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EndGameApplication.class.getResource("end_game_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1089, 670);
        stage.setTitle("Become An Accountant");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
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