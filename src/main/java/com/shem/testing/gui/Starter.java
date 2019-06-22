package com.shem.testing.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass()
                    .getResource("/gui/scene.fxml"));
            primaryStage.setTitle("Testing SH");
            primaryStage.setScene(new Scene(root));
            primaryStage.setOnCloseRequest(event -> System.exit(0));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guiLaunch(){
        launch();
    }
}
