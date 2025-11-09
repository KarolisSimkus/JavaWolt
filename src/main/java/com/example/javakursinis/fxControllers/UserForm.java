package com.example.javakursinis.fxControllers;

import com.example.javakursinis.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserForm {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField pswField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField phoneNumberField;
    @FXML
    public ListView<User> userListField;

    public void createUser(ActionEvent actionEvent) {
        User user = new User(usernameField.getText(),
        pswField.getText(), nameField.getText(), surnameField.getText(),
        phoneNumberField.getText());
        System.out.println(user);
        userListField.getItems().add(user);
    }
}
