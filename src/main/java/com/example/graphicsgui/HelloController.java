package com.example.graphicsgui;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class HelloController {

    @FXML
    private ImageView imageView;
    private Image tempImage;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label mode;

    @FXML
    private BorderPane basePane;

    @FXML
    private RadioButton radio1, radio2;

    private boolean lightMode = true;

    private Stage aboutWindow = null;

    private BufferedImage toBufferedImage(Image image)
    {
        PixelReader reader = image.getPixelReader();
        BufferedImage bufferedImage = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color clr = reader.getColor(x, y);
                int rgb = ((int)(clr.getRed() * 255) << 16) + ((int)(clr.getGreen() * 255) << 8) + ((int)(clr.getBlue() * 255));
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        return bufferedImage;
    }

    @FXML
    private void negativeButtonAction() {
        if(imageView.getImage() != null)
            imageView.setImage(Filters.Negative(imageView.getImage()));
    }

    @FXML
    private void bwfilterButtonAction() {
        if(imageView.getImage() != null)
            imageView.setImage(Filters.BlackAndWhite(imageView.getImage()));
    }

    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }
    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(".jpg .bmp .png", "*.jpg", "*.bmp", "*.png", "*.jpeg");   //vyfiltrování správných formátů
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
                radio1.setDisable(false);
                radio2.setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image As");

        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", ".png");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpg)", ".jpg", ".jpeg");
        FileChooser.ExtensionFilter bmpFilter = new FileChooser.ExtensionFilter("BMP files (*.bmp)", ".bmp");
        fileChooser.getExtensionFilters().addAll(pngFilter, jpgFilter, bmpFilter);

        File file = fileChooser.showSaveDialog(null);
        String extension = (fileChooser.getSelectedExtensionFilter().getExtensions().get(0)).substring(1);
        System.out.println(extension);
        if (file != null) {
            try {
                var asd = fileChooser.getSelectedExtensionFilter().getExtensions();
                ImageIO.write(toBufferedImage(imageView.getImage()), extension, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aboutButtonAction() throws IOException{

        //pokud už jedno okno je, tak se nevytváří znovu, jen se dá dopředu
        if(aboutWindow != null && aboutWindow.isShowing())
        {
            aboutWindow.toFront();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));

        aboutWindow = new Stage();

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);



        Image icon = new Image(getClass().getResource("photos/icon.png").toExternalForm());
        aboutWindow.getIcons().add(icon);

        aboutWindow.setTitle("About");
        aboutWindow.setScene(scene);

        aboutWindow.show();

        aboutWindow.setMinWidth(600);
        aboutWindow.setMinHeight(400);

        aboutWindow.setMaxWidth(600);
        aboutWindow.setMaxHeight(400);
    }

    @FXML
    private void nightButtonAction() {
        if (lightMode) {
            lightMode = false;
            menuBar.setStyle("-fx-background-color: #B6BBC4;");
            basePane.setStyle("-fx-background-color: #161A30;");
            radio1.setStyle("-fx-background-color: #B6BBC4;");
            radio2.setStyle("-fx-background-color: #B6BBC4;");
            mode.setText("Light Mode");
        }
        else {
            lightMode = true;
            menuBar.setStyle("-fx-background-color: ;");
            basePane.setStyle("-fx-background-color: ;");
            radio1.setStyle("-fx-background-color: ;");
            radio2.setStyle("-fx-background-color: ;");
            mode.setText("Dark Mode");
        }
    }
}