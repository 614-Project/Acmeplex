package com.example.Acmeplex.entities;

import com.example.Acmeplex.enums.Genre;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ElementCollection
    @CollectionTable(name = "MOVIE_CAST", joinColumns = @JoinColumn(name = "movieId"))
    private List<String> cast;

    private String bannerUrl;
    private String carouselUrl;

    private String trailerUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Show> shows = new ArrayList<>();

    //movie constructor
    public Movie(String title, String description, Integer duration, Genre genre, 
             LocalDate releaseDate, Double rating, 
             String director, List<String> cast, String bannerUrl, String carouselUrl, String trailerUrl) {
    this.title = title;
    this.description = description;
    this.duration = duration;
    this.genre = genre;
    this.releaseDate = releaseDate;
    this.rating = rating;
    this.director = director;
    this.cast = cast;
    this.bannerUrl = bannerUrl;
    this.carouselUrl = carouselUrl;
    this.trailerUrl = trailerUrl;
}

}
