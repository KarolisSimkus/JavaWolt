package com.example.javakursinis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuisine {
    private int id;
    private Ingredients ingredients;
    private Allergens allergens;
    private PortionSize portionSize;
    private double price;
}
