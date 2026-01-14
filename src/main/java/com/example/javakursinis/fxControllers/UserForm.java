package com.example.javakursinis.fxControllers;

import com.example.javakursinis.hibernateControllers.GenericHibernate;
import com.example.javakursinis.model.Restaurant;
import com.example.javakursinis.model.User;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public RadioButton userRadio;
    public RadioButton restaurantRadio;
    public RadioButton clientRadio;
    public RadioButton driverRadio;
    public TextField addressField;
     // laikinas
    public Button updateButton;

    private User userForUpdate;
    private EntityManagerFactory factory;
    private GenericHibernate genericHibernate;
    private boolean isForUpdate;

    public void setData(EntityManagerFactory factory, User user, boolean isForUpdate) {
        this.factory = factory;
        this.genericHibernate = new GenericHibernate(factory);
        this.userForUpdate = user;
        this.isForUpdate = isForUpdate;
        fillUserDataForUpdate();
    }

    private void fillUserDataForUpdate() {
        if(userForUpdate != null && isForUpdate) {
            if(userForUpdate instanceof User){
                nameField.setText(userForUpdate.getLogin());
                pswField.setText(userForUpdate.getPassword());
            }
        }else{
            updateButton.setVisible(false);
        }
    }

    public void createUser(ActionEvent actionEvent) {

        if(userRadio.isSelected()) {
            User user = new User(usernameField.getText(),
                    pswField.getText(), nameField.getText(), surnameField.getText(),
                    phoneNumberField.getText());
            genericHibernate.create(user);
            userListField.getItems().add(user);
        } else if (restaurantRadio.isSelected()) {
            Restaurant restaurant = new Restaurant(usernameField.getText(),
                    pswField.getText(), nameField.getText(), surnameField.getText(),
                    phoneNumberField.getText());
            genericHibernate.create(restaurant);
        }else if (clientRadio.isSelected()) {

        }else if (driverRadio.isSelected()) {

        }
    }

    public void disableFields() {
        if(userRadio.isSelected()) {
            addressField.setDisable(true);
        } else if (restaurantRadio.isSelected()) {
            addressField.setDisable(false);
        }else if (clientRadio.isSelected()) {

        }else if (driverRadio.isSelected()) {

        }
    }

    public void updateUser(ActionEvent actionEvent) {
    }
}
