package com.example.javakursinis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Restaurant {
    protected List<Cuisine> dishes;
    protected String workHours;
    protected double rating;
}
