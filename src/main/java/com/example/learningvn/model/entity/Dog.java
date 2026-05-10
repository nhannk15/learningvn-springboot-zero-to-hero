package com.example.learningvn.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    @Size(min = 5, message = "Dog name's length must be greater than 5")
    private String name;

    @Column(name = "color", columnDefinition = "VARCHAR(100)", nullable = false)
    @NotBlank(message = "Color must not be left blank")
    private String color;

    @Column(name = "date_born")
    private LocalDateTime dateBorn;

    @PrePersist
    public void onCreate() {
        this.dateBorn = LocalDateTime.now();
    }

    public Dog() {
    }

    public Dog(String name, String color, LocalDateTime dateBorn) {
        this.name = name;
        this.color = color;
        this.dateBorn = dateBorn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(LocalDateTime dateBorn) {
        this.dateBorn = dateBorn;
    }

    @Override
    public String toString() {
        return "Dog [id=" + id + ", name=" + name + ", color=" + color + ", dateBorn=" + dateBorn + "]";
    }

}
