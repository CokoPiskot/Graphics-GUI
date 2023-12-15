package com.example.graphicsgui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;


public class HelloController {


    @FXML
    private ImageView imageView;

    @FXML
    private Image originalImage;

    @FXML
    private Image tempImage;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label mode;

    @FXML
    private BorderPane basePane;

    @FXML
    private RadioButton radio1, radio2;

    @FXML
    private TextArea textArea;

    private boolean lightMode = true;

    private Clip clip;

    private Stage aboutWindow = null;


    private BufferedImage toBufferedImage(Image image) {
        PixelReader reader = image.getPixelReader();
        BufferedImage bufferedImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color clr = reader.getColor(x, y);
                int rgb = ((int) (clr.getRed() * 255) << 16) + ((int) (clr.getGreen() * 255) << 8) + ((int) (clr.getBlue() * 255));
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        return bufferedImage;
    }

    @FXML
    private void negativeButtonAction() {
        clickSound();
        radio2.setSelected(true);
        textArea.appendText("\nNegative filter loaded");
        if (imageView.getImage() != null)
            tempImage = Filters.Negative(imageView.getImage());
        imageView.setImage(tempImage);
    }

    @FXML
    private void bwfilterButtonAction() {
        clickSound();
        radio2.setSelected(true);
        textArea.appendText("\nB-W filter loaded");
        if (imageView.getImage() != null)
            tempImage = Filters.BlackAndWhite(imageView.getImage());
        imageView.setImage(tempImage);
    }

    @FXML
    private void exitButtonAction() {
        clickSound();
        Platform.exit();
    }

    @FXML
    private void selectImage() {
        clickSound();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(".jpg .bmp .png", "*.jpg", "*.bmp", "*.png", "*.jpeg");   //vyfiltrování správných formátů
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                originalImage = new Image(new FileInputStream(selectedFile));
                imageView.setImage(originalImage);
                tempImage = originalImage;
                radio1.setDisable(false);
                radio2.setDisable(false);
                textArea.setText(selectedFile.getName() + " loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveImage() throws IOException {
        clickSound();
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
                var asd = fileChooser.getSelectedExtensionFilter().getExtensions();     //?
                ImageIO.write(toBufferedImage(imageView.getImage()), extension, file);
                textArea.appendText("\n" + file.getName() + "saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aboutButtonAction() throws IOException {

        clickSound();
        playMusic();

        //pokud už jedno okno je, tak se nevytváří znovu, jen se dá dopředu
        if (aboutWindow != null && aboutWindow.isShowing()) {
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

        if (!lightMode) {
            Label classText = (Label) scene.lookup("#classText");
            Label authorsText = (Label) scene.lookup("#authorsText");
            Label nameText = (Label) scene.lookup("#nameText");
            Label date = (Label) scene.lookup("#date");
            classText.setTextFill(Color.WHITE);
            authorsText.setTextFill(Color.WHITE);
            nameText.setTextFill(Color.WHITE);
            date.setTextFill(Color.WHITE);
            scene.getRoot().setStyle("-fx-background-color: #161A30;");
        }
        else {
            Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};

            Timeline timeline = new Timeline();
            for (int i = 0; i < colors.length; i++) {
                final Color color = colors[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), new KeyValue(scene.getRoot().styleProperty(), "-fx-background-color: rgba("
                        + (color.getRed() * 255) + ","
                        + (color.getGreen() * 255) + ","
                        + (color.getBlue() * 255) + ", 1.0);"));
                timeline.getKeyFrames().add(keyFrame);
            }
            timeline.setCycleCount(Timeline.INDEFINITE);

            // Play the color-cycling animation
            timeline.play();
        }

        aboutWindow.setOnCloseRequest(windowEvent -> clip.stop());

        ImageView aboutImageView = (ImageView)  scene.lookup("#aboutImageView");
        ImageView aboutImageView_2 = (ImageView)  scene.lookup("#aboutImageView_2");


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5), aboutImageView);
        translateTransition.setToX(50);
        translateTransition.setToY(-50);

        TranslateTransition translateTransition_2 = new TranslateTransition(Duration.seconds(5), aboutImageView_2);
        translateTransition_2.setToX(130);

        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);

        translateTransition.play();
        translateTransition_2.play();

    }

    @FXML
    private void nightButtonAction() {
        clickSound();
        if (lightMode) {
            lightMode = false;
            menuBar.setStyle("-fx-background-color: #B6BBC4;");
            basePane.setStyle("-fx-background-color: #161A30;");
            radio1.setStyle("-fx-background-color: #B6BBC4;");
            radio2.setStyle("-fx-background-color: #B6BBC4;");
            mode.setText("Light Mode");
        } else {
            lightMode = true;
            menuBar.setStyle("-fx-background-color: ;");
            basePane.setStyle("-fx-background-color: ;");
            radio1.setStyle("-fx-background-color: ;");
            radio2.setStyle("-fx-background-color: ;");
            mode.setText("Dark Mode");
        }
    }

    @FXML
    private void radioButtonController() {
        clickSound();
        if (radio1.isSelected()) {
            imageView.setImage(originalImage);
        }
        else if(radio2.isSelected()) {
            imageView.setImage(tempImage);
        }
    }

    @FXML
    private void restoreOriginalImage() {
        clickSound();
        radio1.setSelected(true);
        tempImage = originalImage;
        imageView.setImage(originalImage);
        textArea.setText("Image restored");
    }

    private void clickSound() {
        String path = Objects.requireNonNull(getClass().getResource("audio/audio.wav")).getPath();
        try {
            File musicPath = new File(path);
            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playMusic() {
        String path = Objects.requireNonNull(getClass().getResource("audio/music.wav")).getPath();
        try {
            File musicPath = new File(path);
            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}