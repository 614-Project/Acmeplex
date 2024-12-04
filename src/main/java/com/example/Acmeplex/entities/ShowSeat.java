package com.example.Acmeplex.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SHOW_SEATS")
@Data
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    private Integer price;

    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "theatre_seat_fk", nullable = false)
    private TheaterSeat theatreSeat;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Show show;
}