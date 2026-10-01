package com.example.medicalstore.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int stock;

    private LocalDateTime addedTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Medicine() {
    }

    @PrePersist
    public void setTime() {
        addedTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}