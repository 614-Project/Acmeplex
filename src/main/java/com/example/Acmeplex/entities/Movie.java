package com.example.Acmeplex.entities;

import com.example.Acmeplex.enums.Genre;
import com.example.Acmeplex.enums.MovieType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MOVIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "movie_id")
    private Integer movieId; // Primary Key

    @Column(nullable = false)
    private String title; // Movie title

    @Column(length = 1000) // Allows a longer description
    private String description; // Details of the movie

    private Integer duration; // Duration in minutes

    @Enumerated(value = EnumType.STRING)
    private Genre genre; // Genre of the movie (e.g., DRAMA, ACTION)

    private LocalDate releaseDate;

    // @Column(scale = 1, precision = 3) // Allows decimal precision like 8.5
    private Double rating; // Movie rating

    private String director; // Director's name

    @Enumerated(value = EnumType.STRING)
    private MovieType movieType;

    @ElementCollection
    @CollectionTable(name = "MOVIE_CAST", joinColumns = @JoinColumn(name = "movieId"))
    //@Column(name = "cast_member")
    private List<String> cast;

    private String url; 

    private String trailerUrl;
}

