package com.example.Acmeplex.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THEATER_SEATS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;
    
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Theater theater;

    public TheaterSeat(String address, Integer numberOfSeatPerRow, Integer numberOfRows) {
    }
}