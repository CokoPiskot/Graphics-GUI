package com.example.graphicsgui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import javafx.scene.Parent;



public class HelloApplication extends Application {

    @FXML

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        HelloController controller = fxmlLoader.getController();
        //controller.resizableTextArea();

        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setTitle("Graphics-GUI");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}