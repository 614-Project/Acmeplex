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
    
    @Column(nullable = false)
    private String password;
    
    // Boolean to indicate if the user is a Registered User (RU)
 
    // Relationships

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    // private List<Reservation> reservations = new ArrayList<>(); // A user can create multiple reservations
 
    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    // private Membership membership; // A Registered User (RU) can have an annual membership

}

 