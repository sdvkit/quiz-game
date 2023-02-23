package com.sulitsa.dev.accountant.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class StartGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartGameApplication.class.getResource("start_game_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1089, 670);
        stage.setTitle("Become An Accountant");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}