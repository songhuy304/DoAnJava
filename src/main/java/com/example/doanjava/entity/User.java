package com.example.doanjava.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Email")
    private String Email;
    @Column(name = "UserName")
    private String UserName;

    @Column(name = "Password")
    private String Password;
}
