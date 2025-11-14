package com.example.javakursinis.fxControllers;

import com.example.javakursinis.HelloApplication;
import com.example.javakursinis.Utils.FxUtils;
import com.example.javakursinis.hibernateControllers.CustomHibernate;
import com.example.javakursinis.hibernateControllers.GenericHibernate;
import com.example.javakursinis.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("kursinis");

    public void validateAndLoad(ActionEvent actionEvent) throws IOException {


        CustomHibernate customHibernate = new CustomHibernate(factory);
        User user = customHibernate.getUserByCredentials(loginField.getText(), passwordField.getText());
        if(user != null) {
            // Sekme
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) passwordField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else{
            // alertai
            FxUtils.generateAlert(Alert.AlertType.WARNING, "Error", "User Login", "Error message");
        }

    }

    public void registerNewUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-form.fxml"));

        Parent parent = fxmlLoader.load();
        UserForm userForm = fxmlLoader.getController();
        userForm.setData(factory);

        Scene scene = new Scene(parent);


        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
