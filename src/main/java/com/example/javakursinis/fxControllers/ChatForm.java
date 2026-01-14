package com.example.javakursinis.fxControllers;

import com.example.javakursinis.hibernateControllers.CustomHibernate;
import com.example.javakursinis.model.FoodOrder;
import com.example.javakursinis.model.User;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ChatForm {
    public ListView messageList;
    public TextArea messageBody;

    private EntityManagerFactory emf;
    private CustomHibernate customHibernate;

    private User user;
    private FoodOrder currentFoodOrder;

    public void SetData(EntityManagerFactory emf, User user, FoodOrder FO) {
        this.emf = emf;
        this.user = user;
        this.currentFoodOrder = FO;
        this.customHibernate = new CustomHibernate(emf);
    }

    public void sendMessage(ActionEvent actionEvent) {
    }
}
