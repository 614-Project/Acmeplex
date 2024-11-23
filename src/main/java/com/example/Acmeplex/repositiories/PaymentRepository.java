package com.example.Acmeplex.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Acmeplex.entities.PaymentIntentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentIntentEntity, Long> {

    PaymentIntentEntity findByEmail(String email);
    //Payment findByUserEmail(String userEmail);
}