package com.example.graphicsgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;




public class HelloController {

    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }

    @FXML
    private void aboutButtonAction() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setMinWidth(600);
        stage.setMinHeight(400);

        stage.setMaxWidth(600);
        stage.setMaxHeight(400);

        stage.setTitle("About");
        stage.setScene(scene);

        stage.show();

    }


}