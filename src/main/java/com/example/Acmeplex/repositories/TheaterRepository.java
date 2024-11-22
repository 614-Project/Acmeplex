package com.example.Acmeplex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Acmeplex.entities.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}