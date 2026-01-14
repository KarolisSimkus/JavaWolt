package com.example.javakursinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant extends User{
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Cuisine> menuItems;
    protected String workHours;
    protected double rating;
    @Enumerated
    private CuisineType cuisineType;
    private double deliveryFee;
    private int estimatedDeliveryTime;
    private LocalDate openingTime;
    private LocalDate closingTime;
    private boolean isOpen;
    private int totalReviews;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodOrder> myOrders;

    public Restaurant(int id, String login, String password, String name, String surname, String phoneNumber, LocalDate dateCreated, LocalDate dateUpdated, boolean isAdmin) {
        super(id, login, password, name, surname, phoneNumber, dateCreated, dateUpdated, isAdmin);
        this.menuItems = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.isOpen = true;
        this.totalReviews = 0;
        this.rating = 0.0;
    }

    public Restaurant(String login, String password, String name, String surname, String phoneNumber) {
        super(login, password, name, surname, phoneNumber);
    }
}
