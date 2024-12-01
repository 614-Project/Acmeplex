package com.example.Acmeplex.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

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


    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Show show;
}