package com.example.pengmai.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class User {
    @Id
    private String userId;
    @Value("false")
    private boolean userIdentity;
    @Column(unique = true,nullable = false)
    private String username;
    private String password;

    public User(String userId, String username, String password, Boolean identity) {
        this.userId=userId;
        this.password=password;
        this.userIdentity=identity;
        this.username=username;
    }

    public User() {

    }
}
