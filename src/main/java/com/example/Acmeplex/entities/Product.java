package com.example.Acmeplex.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "status")
    private String status = "pending";

    @Column(name = "amount")
    private Long amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "customerEmail")
    private String customerEmail;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "expireDate")
    private LocalDateTime expireDate;

}
