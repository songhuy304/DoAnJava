package com.example.doanjava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Category;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.doanjava.Validator.annotation.ValidUsername;

import java.util.*;

@Data

@Entity
@Table(name = "user")
public class User {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @NotBlank(message = "Username is required")
    @Size(max = 50,message = "Username must be less than 50 characters")
    @ValidUsername
    private String username;
    @Column(name = "password", length = 250,nullable = false)
    @NotBlank(message = "Password is required")

    private String password;
    @Column(name = "email",length = 50)
    @Size(max = 50,message = "Email must be less than 50 characters")
    @Email(message = "Email should be valid")

    private String email;
    @Column(name = "name",length = 50, nullable = false)
    @Size(max = 50,message = "Your name must be less than 50 characters")
    @NotBlank(message = "Your name is required")


    private String name;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();
    @Column(name = "provider")
    private String provider;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<product> books = new ArrayList<>();
}
