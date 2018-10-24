package com.robertson.app.javaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("validator.fxml"));
        stage.setResizable(false);
        stage.setTitle("Password Validator");
        stage.setScene(new Scene(root, 600, 325));
        stage.setMinWidth(600);
        stage.setMinHeight(325);
        stage.show();
    }


    public static void main(String[] args) {
        // Call the Application launch method.
        launch(args);
    }
}
