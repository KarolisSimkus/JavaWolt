package com.example.javakursinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne
    protected BasicUser sender;
    @ManyToOne
    protected BasicUser receiver;
    @ManyToOne
    protected Chat chat;
    protected String message;
    protected LocalDateTime timestamp;
    protected boolean isRead;

}
