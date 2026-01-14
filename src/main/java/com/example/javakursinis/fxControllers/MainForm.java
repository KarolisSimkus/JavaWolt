package com.example.javakursinis.fxControllers;

import com.example.javakursinis.HelloApplication;
import com.example.javakursinis.hibernateControllers.CustomHibernate;
import com.example.javakursinis.model.*;
import com.mysql.cj.xdevapi.Client;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainForm implements Initializable {
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
    @FXML
    public TabPane tabPaneField;

    //<editor-fold desc="User tab elements">
    @FXML
    public TableColumn<UserTableParameters, Integer> idCol;
    @FXML
    public TableColumn<UserTableParameters, String> loginCol;
    @FXML
    public TableColumn<UserTableParameters, String> pswCol;
    @FXML
    public TableColumn<UserTableParameters, String> nameCol;
    @FXML
    public TableColumn<UserTableParameters, String> surnameCol;
    @FXML
    public TableColumn<UserTableParameters, Void> actionCol; // bus kitas išlaužymas
    @FXML
    public TableColumn<UserTableParameters, String> historyCol;
    @FXML
    public TableView<UserTableParameters> userTable;
    @FXML
    public TableColumn<UserTableParameters, String> addressCol;
    @FXML
    public TableColumn<UserTableParameters, String> userTypeCol;
    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public ComboBox<Restaurant> restaurantField;
    @FXML
    public ComboBox<BasicUser> clientList;
    @FXML
    public ListView<FoodOrder> orderList;
    @FXML
    public ListView<BasicUser> basicUserList;
    public ComboBox<OrderStatus> orderStatusField;
    public ComboBox<OrderStatus> filterStatus;
    public ComboBox<BasicUser> filterClient;
    public DatePicker filterFrom;
    public DatePicker filterTo;
    public ListView<Cuisine> cuisineList;
    public ListView<Cuisine> foodList;

    //Cuisine tab elements
    public ListView<Restaurant> restaurantList;
    public TextField titleCuisineField;
    public TextArea ingredientsField;
    public TextField cuisinePriceField;


    private ObservableList<UserTableParameters> data = FXCollections.observableArrayList();
    //</editor-fold> elements

    private EntityManagerFactory emf;
    private CustomHibernate customHibernate;
    private User currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTable.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        pswCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        pswCol.setCellFactory(TextFieldTableCell.forTableColumn());
        pswCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPassword(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setPassword(event.getNewValue());
            customHibernate.update(user);
        });
        //addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

    }
    public void setData(EntityManagerFactory emf, User user){
        this.emf = emf;
        this.customHibernate = new CustomHibernate(emf);
        this.currentUser = user;
        setUserFormVisibility();
        reloadTableData();
    }

    private void setUserFormVisibility() {
        if(currentUser instanceof User){
            // Adminas viska mato
        }
        else if(currentUser instanceof Restaurant){
            tabPaneField.getTabs().remove(userTab);
            tabPaneField.getTabs().remove(altTab);
        }else if(currentUser instanceof Driver){
            // Driveris tik savo orderi turi matyti bei paskyra
        }else if(currentUser instanceof BasicUser){
            // Klientas tik savo orderi turi matyti bei paskyra
        }else {
            // If smthg else nieko nerodyk
            tabPaneField.getTabs().remove(userTab);
            tabPaneField.getTabs().remove(altTab);
            tabPaneField.getTabs().remove(menuTab);
            tabPaneField.getTabs().remove(restaurantTab);
        }
    }

    public void reloadTableData() {
        if(userTab.isSelected()) {
            userTable.getItems().clear();
            List<User> users = customHibernate.getAllRecords(User.class);
            for(User user : users) {
                UserTableParameters uTP = new UserTableParameters();
                uTP.setId(user.getId());
                uTP.setUserType(user.getClass().getSimpleName());
                uTP.setLogin(user.getLogin());
                uTP.setPassword(user.getPassword());
                uTP.setName(user.getName());
                uTP.setSurname(user.getSurname());
                if(user instanceof BasicUser) {
                    uTP.setAddress(((BasicUser) user).getAddress());
                }if(user instanceof Restaurant) {

                }if(user instanceof Driver) {

                }
                data.add(uTP);
                //userTable.getItems().add(uTP);
            }
            userTable.getItems().addAll(data);
        }else if(restaurantTab.isSelected()) {
            clearAllOrderFields();
            List<FoodOrder> foodOrders = getFoodOrders();
            orderList.getItems().addAll(foodOrders);
            clientList.getItems().addAll(customHibernate.getAllRecords(BasicUser.class));
            basicUserList.getItems().addAll(customHibernate.getAllRecords(BasicUser.class));
            restaurantField.getItems().addAll(customHibernate.getAllRecords(Restaurant.class));
            orderStatusField.getItems().addAll(OrderStatus.values());
            //foodList.getItems().addAll(customHibernate.getAllRecords(Cuisine.class));
        }else if(menuTab.isSelected()) {
            clearAllCuisineFields();
            restaurantList.getItems().addAll(customHibernate.getAllRecords(Restaurant.class));
        }else if(altTab.isSelected()) {
            List<User> userList1 = customHibernate.getAllRecords(User.class);
            userList.getItems().clear();
            userList.getItems().addAll(userList1);
        }
    }

    private void clearAllCuisineFields() {
        foodList.getItems().clear();
        cuisinePriceField.clear();
        titleCuisineField.clear();
        restaurantField.getItems().clear();
        ingredientsField.clear();
    }
    private List<FoodOrder> getFoodOrders(){
        if(currentUser instanceof Restaurant){
            return customHibernate.getRestaurantOrders((Restaurant) currentUser);
        }else {
            return customHibernate.getAllRecords(FoodOrder.class);
        }
    }


    public void openUserForm() throws IOException {

    }

    public void addUser() throws IOException {
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

    //Update
    public void loadUser() throws IOException {
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

    public void deleteUser() {
        User selectedUser = (User) userList.getSelectionModel().getSelectedItem();
        customHibernate.deleteById(User.class, selectedUser.getId());
    }

    public void createOrder(ActionEvent actionEvent) {
        if(isNumeric(priceField.getText())) {
            /*FoodOrder FoodOrder = new FoodOrder(
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    clientList.getValue(),
                    restaurantField.getValue()
            );*/
            FoodOrder FoodOrder = new FoodOrder(
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    clientList.getValue(),
                    foodList.getSelectionModel().getSelectedItems(),
                    restaurantField.getValue()
            );
            customHibernate.create(FoodOrder);
            fillOrderList();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    public void updateOrder(ActionEvent actionEvent) {
        FoodOrder foodOrder = (FoodOrder) orderList.getSelectionModel().getSelectedItem();
        foodOrder.setName(nameField.getText());
        foodOrder.setPrice(Double.parseDouble(priceField.getText()));
        foodOrder.setRestaurant(restaurantField.getSelectionModel().getSelectedItem());
        foodOrder.setCustomer(clientList.getSelectionModel().getSelectedItem());
        foodOrder.setStatus(orderStatusField.getValue());

        customHibernate.update(foodOrder);

        fillOrderList();
    }

    public void deleteOrder(ActionEvent actionEvent) {
        FoodOrder foodOrder = (FoodOrder) orderList.getSelectionModel().getSelectedItem();

        customHibernate.delete(FoodOrder.class, foodOrder.getId());

        fillOrderList();
    }

    private void fillOrderList(){
        orderList.getItems().clear();
        orderList.getItems().addAll(customHibernate.getAllRecords(FoodOrder.class));
    }

    private void clearAllOrderFields() {
        orderList.getItems().clear();
        clientList.getItems().clear();
        basicUserList.getItems().clear();
        restaurantField.getItems().clear();
        nameField.clear();
        priceField.clear();
    }

    public void loadOrderInfo(MouseEvent mouseEvent) {
        FoodOrder selectedOrder = (FoodOrder) orderList.getSelectionModel().getSelectedItem();
        clientList.getItems().stream()
                .filter(c -> c.getId() == selectedOrder.getCustomer().getId())
                .findFirst()
                .ifPresent(u -> clientList.getSelectionModel().select(u));
        basicUserList.getItems().stream()
                .filter(c -> c.getId() == selectedOrder.getCustomer().getId())
                .findFirst()
                .ifPresent(u -> basicUserList.getSelectionModel().select(u));
        nameField.setText(selectedOrder.getName());
        priceField.setText(String.valueOf(selectedOrder.getPrice()));
        restaurantField.getItems().stream()
                .filter(r -> r.getId() == selectedOrder.getCustomer().getId())
                .findFirst()
                .ifPresent(u -> restaurantField.getSelectionModel().select(u));
        // orderStatusField.getItems().stream()
        // 2ia disable enable reiketu
        disableFoodOrderFields();

    }

    private void disableFoodOrderFields() {
        if(orderStatusField.getSelectionModel().getSelectedItem() == OrderStatus.COMPLETED) {
            clientList.setDisable(true);
            basicUserList.setDisable(true);
            restaurantField.setDisable(true);
            priceField.setDisable(true);
        }
    }

    public void filterOrders(ActionEvent actionEvent) {
    }

    public void createNewMenuItem(ActionEvent actionEvent) {
    Cuisine cuisine = new Cuisine(
            titleCuisineField.getText(),
            ingredientsField.getText(),
            Double.parseDouble(cuisinePriceField.getText()),
            restaurantList.getSelectionModel().getSelectedItem());

    customHibernate.create(cuisine);
    }

    public void updateMenuItem(ActionEvent actionEvent) {
    }

    public void loadRestaurantMenu(MouseEvent mouseEvent) {
        cuisineList.getItems().clear();
        cuisineList.getItems().addAll(customHibernate.getRestaurantCuisine(restaurantField.getSelectionModel().getSelectedItem()));
    }

    public void loadRestaurantMenuForOrder(ActionEvent actionEvent) {
        foodList.getItems().clear();
        foodList.getItems().addAll(customHibernate.getRestaurantCuisine(restaurantField.getSelectionModel().getSelectedItem()));
    }
}
