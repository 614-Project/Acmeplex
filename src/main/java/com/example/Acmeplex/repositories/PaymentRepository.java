package com.example.Acmeplex.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Acmeplex.entities.Payment;

@Repository
public interface  PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByConfirmationId(String email);
}
