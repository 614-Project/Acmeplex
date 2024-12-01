package com.example.Acmeplex.entities;

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

    // private Boolean isFoodContains;

    @ManyToOne
    @JoinColumn
    private Show show;
}