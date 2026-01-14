package com.example.javakursinis.model;

import com.example.javakursinis.fxControllers.UserTableParameters;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @ManyToMany(mappedBy = "orders", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Cuisine> items;
    private double price;
    private String name;

    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Restaurant restaurant;
    @OneToOne(cascade = CascadeType.ALL)
    private Chat chat;
    //private List<OrderItem>
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDate dateCreated;
    private LocalDate dateUpdated;


    public FoodOrder(String name, double price, BasicUser customer, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public FoodOrder(String name, double price, BasicUser customer, List<Cuisine> items, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.customer = customer;
        this.items = items;
        this.restaurant = restaurant;
        this.status = OrderStatus.PENDING;
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
