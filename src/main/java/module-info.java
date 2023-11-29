module com.example.graphicsgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graphicsgui to javafx.fxml;
    exports com.example.graphicsgui;
}