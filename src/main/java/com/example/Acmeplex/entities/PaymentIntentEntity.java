package com.example.Acmeplex.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_intent")
@Data
public class PaymentIntentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_intent_id")
    private String paymentIntentId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status")
    private String status;

    @Column(name = "email")
    private String email;

    // Additional fields as needed
}
