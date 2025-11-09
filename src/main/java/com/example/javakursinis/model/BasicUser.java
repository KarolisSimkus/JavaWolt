package com.example.javakursinis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BasicUser extends User {

    protected String address;
    @OneToMany()
    protected List<FoodOrder> myOrders;
    @OneToMany()
    protected List<Review> myReviews;
    @Transient
    protected List<Review> feedback;

    public BasicUser(String login, String password, String name, String surname, String phoneNumber, String address) {
        super(login, password, name, surname, phoneNumber);
        this.address = address;
        this.myOrders = new ArrayList<>();
        this.feedback = new ArrayList<>();
        this.myReviews = new ArrayList<>();
    }
}
