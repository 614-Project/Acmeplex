package com.example.Acmeplex.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Acmeplex.entities.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);

    Optional<Theater> findByNameIgnoreCase(String name);

}