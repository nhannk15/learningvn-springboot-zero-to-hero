package com.example.learningvn.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DogDTO {

    @Size(min = 5, message = "Dog name's length must be greater than 5")
    private String name;

    @NotBlank(message = "Dog's color can't be left blank")
    private String color;

    public DogDTO() {
    }

    public DogDTO(String name, String color) {
        this.name = name;
        this.color = color;
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

    @Override
    public String toString() {
        return "DogDTO [name=" + name + ", color=" + color + "]";
    }

}
