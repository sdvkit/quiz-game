package com.sulitsa.dev.accountant.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CallFriendApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(CallFriendApplication.class.getResource("call_friend_form.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), 249, 489);
        stage.setTitle("Calling...");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}