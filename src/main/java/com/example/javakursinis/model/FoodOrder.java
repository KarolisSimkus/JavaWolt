package com.example.javakursinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cuisine> items;
    private double price;

    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Restaurant restaurant;
    @OneToOne
    private Chat chat;
    //private List<OrderItem>
}
