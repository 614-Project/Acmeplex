package com.example.Acmeplex.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity

@Table(name = "USERS")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
 
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private Integer userId; // Unique identifier for each user
 
    @Column(nullable = false)
    private String name; // Name of the user
 
    @Column(nullable = false, unique = true)
    private String email; // Email address for notifications and receipts
 
    @Column()
    private String phone; // Contact number
 
    @Column()
    private String address; // Address for registered users
 
    // @Column(nullable = false) 
    // private Boolean isRegistered = false;
    @Column(name = "Credit", columnDefinition = "int default 0")
    private Integer credit; 

    @Column(nullable = false)
    private String password;
    

}

 