package com.example.Acmeplex.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "THEATERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Integer theaterId;

    private String name;

    @Column(unique = true)
    private String address;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Show> showList = new ArrayList<>();

    public Theater(String name, String location) {
    this.name = name;
    this.address = location;
}

}