package com.example.graphicsgui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private AnchorPane pane;

    @FXML
    private TextArea textArea;

    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }

    @FXML
    public void resizableTextArea() {
        pane.getChildren().add(textArea);
    }

    @FXML
    private void aboutButtonAction(){
    }


}