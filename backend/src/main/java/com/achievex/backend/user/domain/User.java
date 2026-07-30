package com.achievex.backend.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String steamUsername;
    private String steamProfileUrl;
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public User(String username, String email, String steamProfileUrl, String steamId,String steamUsername, Date createdAt, Date updatedAt) {
        this.username = username;
        this.email = email;
        this.steamProfileUrl = steamProfileUrl;
        this.steamId = steamId;
        this.steamUsername = steamUsername;
        this.createdAt = createdAt;
    }

    public User() {

    }

}
