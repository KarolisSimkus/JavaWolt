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
public class Review extends ChatMessage{
    private int rate;
    private String text;
    @ManyToOne
    private Restaurant restaurant;
    @OneToOne
    private FoodOrder order;
}
