package com.example.javakursinis.fxControllers;

import com.example.javakursinis.HelloApplication;
import com.example.javakursinis.hibernateControllers.CustomHibernate;
import com.example.javakursinis.model.*;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainForm {
    @FXML
    public Tab userTab;
    @FXML
    public Tab restaurantTab;
    @FXML
    public Tab menuTab;
    @FXML // laikinas
    public Tab altTab;
    @FXML // laikinas
    public ListView<User> userList;
    @FXML // laikinas
    public TabPane tabPaneField;

    private EntityManagerFactory emf;
    private CustomHibernate customHibernate;
    private User currentUser;


    public void setData(EntityManagerFactory emf, User user){
        this.emf = emf;
        this.customHibernate = new CustomHibernate(emf);
        this.currentUser = user;
        setUserFormVisibility();
    }

    private void setUserFormVisibility() {
        if(currentUser instanceof User){

        }
        else if(currentUser instanceof Restaurant){
            tabPaneField.getTabs().remove(userTab);
            tabPaneField.getTabs().remove(altTab);
            tabPaneField.getTabs().remove(userTab);
        }else if(currentUser instanceof Driver){

        }else if(currentUser instanceof BasicUser){

        }
    }

    public void reloadTableData() {
        if(userTab.isSelected()) {

        }else if(restaurantTab.isSelected()) {
            List<FoodOrder> foodOrders = getFoodOrders();
        }else if(menuTab.isSelected()) {

        }else if(altTab.isSelected()) {
            List<User> userList1 = customHibernate.getAllRecords(User.class);
            userList.getItems().clear();
            userList.getItems().addAll(userList1);
        }
    }

    private List<FoodOrder> getFoodOrders(){
        if(currentUser instanceof Restaurant){
            return customHibernate.getRestaurantOrders((Restaurant) currentUser);
        }else {
            return customHibernate.getAllRecords(FoodOrder.class);
        }
    }

    //<editor-fold desc="Alt tab func">
    public void openUserForm() throws IOException {

    }

    public void addUser(ActionEvent actionEvent) throws IOException {
        // Sekme
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-form.fxml"));
        Parent parent = fxmlLoader.load();

        User selectedUser = (User) userList.getSelectionModel().getSelectedItem();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(emf, selectedUser, false);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void loadUser(ActionEvent actionEvent) throws IOException {
        // Sekme
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-form.fxml"));
        Parent parent = fxmlLoader.load();

        User selectedUser = (User) userList.getSelectionModel().getSelectedItem();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(emf, selectedUser, true);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void deleteUser(ActionEvent actionEvent) {
        User selectedUser = (User) userList.getSelectionModel().getSelectedItem();
        customHibernate.deleteById(User.class, selectedUser.getId());
    }
    //</editor-fold>
}
