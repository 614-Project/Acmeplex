package com.example.Acmeplex.repositiories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Acmeplex.entities.Credit;

@Repository
public interface  CreditRepository extends JpaRepository<Credit, Long> {

    Optional<Credit> findById(Long id);
}
