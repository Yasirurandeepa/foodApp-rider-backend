package com.example.rider.model;

import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends AuditModel {

    public User() {

    }

    public User(String Username, String Email, String Phone){
        this.username = Username;
        this.email = Email;
        this.phone = Phone;
    }

    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min =1, max = 50)
    private String username;

    @Column(columnDefinition = "text")
    private String password;

    @NotBlank
    @Column(columnDefinition = "text")
    private String email;

    @NotBlank
    @Column(columnDefinition = "text")
    private String phone;

    @Column(columnDefinition = "text")
    private String status;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}