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
@AllArgsConstructor
@NoArgsConstructor
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ingredients;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Allergens> allergens;
    @Enumerated
    private PortionSize portionSize;
    private double price;
    @ManyToMany(mappedBy = "items")
    private List<FoodOrder> orders;
    private String name;

    @ManyToOne
    private Restaurant restaurant;

    public Cuisine(String name, String ingredients, double price, Restaurant restaurant) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.restaurant = restaurant;
    }
}
