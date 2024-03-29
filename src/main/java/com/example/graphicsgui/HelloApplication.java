package com.example.graphicsgui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());      //načtení styles.css


        Image icon = new Image(getClass().getResource("photos/icon.png").toExternalForm());     //načtení ikony
        stage.getIcons().add(icon);

        stage.setTitle("Graphics-GUI");

        //tady to nějak divně funguje, když tady nechám 1000, 700 tak je to o trochu méně z nějakého neznámého důvodu a špatně se poté změnšuje okno, lepší fix než toto jsem nevymyslel
        stage.setMinWidth(1020);
        stage.setMinHeight(740);

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}