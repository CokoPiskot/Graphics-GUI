package com.example.graphicsgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HelloController {

    @FXML
    private ImageView imageView;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label mode;

    @FXML
    private BorderPane basePane;

    private boolean lightMode = true;
    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }
    @FXML
    private void selectImage() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(".jpg .bmp .png", "*.jpg", "*.bmp", "*.png", "*.jpeg");
            fileChooser.getExtensionFilters().add(imageFilter);
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    imageView.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    @FXML
    private void aboutButtonAction() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));

        Stage stage = new Stage();

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);



        Image icon = new Image(getClass().getResource("photos/icon.png").toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("About");
        stage.setScene(scene);

        stage.show();

        stage.setMinWidth(600);
        stage.setMinHeight(400);

        stage.setMaxWidth(600);
        stage.setMaxHeight(400);
    }

    @FXML
    private void nightButtonAction() {
        if (lightMode) {
            menuBar.setStyle("-fx-background-color: darkgrey;");
            basePane.setStyle("-fx-background-color: black;");
            lightMode = false;
            mode.setText("Light Mode");
        }
        else {
                lightMode = true;
                menuBar.setStyle("-fx-background-color: ;");
                basePane.setStyle("-fx-background-color: ;");
                mode.setText("Dark Mode");
        }
    }




}