package com.example.graphicsgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

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

        Image icon = new Image(getClass().getResource("photos/icon.png").toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("About");
        stage.setScene(scene);

        stage.show();

    }




}