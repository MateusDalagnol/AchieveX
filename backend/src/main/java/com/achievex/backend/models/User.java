package com.achievex.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String steamId;
    private String steamProfileUrl;
    private Date createdAt;
    private Date updatedAt;

    public User(String username, String email, String steamProfileUrl, String steamId, Date createdAt, Date updatedAt) {
        this.username = username;
        this.email = email;
        this.steamProfileUrl = steamProfileUrl;
        this.steamId = steamId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {

    }

}
