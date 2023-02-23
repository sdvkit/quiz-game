package com.sulitsa.dev.accountant.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class CommunityHelpApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CommunityHelpApplication.class.getResource("community_help.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Community help");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}