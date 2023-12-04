package com.example.graphicsgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HelloController {



    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }

    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem selectImageButtonMenu;

    @FXML
    private Button selectImageButton;

    @FXML
    private void selectImage() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
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