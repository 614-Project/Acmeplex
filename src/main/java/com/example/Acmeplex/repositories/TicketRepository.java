package com.example.Acmeplex.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Acmeplex.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findBySessionId(String sessionId);
    Optional<Ticket> findById(Long id);
}