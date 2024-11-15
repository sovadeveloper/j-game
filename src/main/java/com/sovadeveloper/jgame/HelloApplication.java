package com.sovadeveloper.jgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameField gameField = new GameField();

        Scene scene = new Scene(gameField, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Hello World");
        stage.show();
        gameField.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}