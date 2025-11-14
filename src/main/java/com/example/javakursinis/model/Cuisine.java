package com.example.javakursinis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated
    private List<Ingredients> ingredients;
    @Enumerated
    private List<Allergens> allergens;
    @Enumerated
    private PortionSize portionSize;
    private double price;
    @ManyToMany
    private List<FoodOrder> orders;
}
