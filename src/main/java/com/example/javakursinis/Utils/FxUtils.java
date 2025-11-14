package com.example.javakursinis.Utils;

import javafx.scene.control.Alert;

public class FxUtils {
    public static void generateAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
