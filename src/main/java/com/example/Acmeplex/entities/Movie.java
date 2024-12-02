package com.example.Acmeplex.entities;

import com.example.Acmeplex.enums.Genre;
import com.example.Acmeplex.enums.MovieType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
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
 
    private Integer movieId;

    private Integer duration; 

    @Enumerated(value = EnumType.STRING)
    private Genre genre; 

    private LocalDate releaseDate;

    private List<String> cast;

    private String url;

    private String trailerUrl;

    private Double rating; 

    private String director; 

    @Column(nullable = false)
    private String title; 

    @Column(length = 1000) 
    private String description; 

    @Enumerated(value = EnumType.STRING)
    private MovieType movieType;

    @ElementCollection
    @CollectionTable(name = "MOVIE_CAST", joinColumns = @JoinColumn(name = "movieId"))

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference 
    private List<Show> shows = new ArrayList<>();

    //movie constructor
    public Movie(String title, String description, Integer duration, Genre genre, 
             MovieType movieType, LocalDate releaseDate, Double rating, 
             String director, List<String> cast, String url, String trailerUrl) {
    this.title = title;
    this.description = description;
    this.duration = duration;
    this.genre = genre;
    this.movieType = movieType;
    this.releaseDate = releaseDate;
    this.rating = rating;
    this.director = director;
    this.cast = cast;
    this.url = url;
    this.trailerUrl = trailerUrl;
}

}
