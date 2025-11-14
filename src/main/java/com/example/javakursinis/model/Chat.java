package com.example.javakursinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private BasicUser customer;
    @ManyToOne
    private Driver driver;
    @OneToOne (mappedBy = "chat", cascade = CascadeType.ALL)
    private FoodOrder foodOrder;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ChatMessage> messages;
    private LocalDateTime createdAt;
    private LocalDateTime lastMessageAt;
    public boolean isActive;

    public Chat() {
        this.messages = new ArrayList<>();
    }
}
