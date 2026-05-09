package com.example.learningvn.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false)
    @Size(min = 5, max = 100, message = "Username must be between 5 - 100 characters")
    private String username;

    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    @Email(message = "Email wrong format")
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false)
    @Size(min = 8, message = "Password length must be greater than 8")
    private String password;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @PrePersist
    public void onCreate() {
        this.dateCreated = LocalDateTime.now();
        this.dateUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.dateUpdated = LocalDateTime.now();
    }

    public User() {
    }

    public User(@Size(min = 5, max = 100, message = "Username must be between 5 - 100 characters") String username,
            @Email(message = "Email wrong format") String email,
            @Size(min = 8, message = "Password length must be greater than 8") String password,
            LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public User(Long id,
            @Size(min = 5, max = 100, message = "Username must be between 5 - 100 characters") String username,
            @Email(message = "Email wrong format") String email,
            @Size(min = 8, message = "Password length must be greater than 8") String password,
            LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + "]";
    }

}
