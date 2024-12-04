package com.example.Acmeplex.repositories;

import com.example.Acmeplex.entities.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer> {
    TheaterSeat findBySeatNo(String name);
}