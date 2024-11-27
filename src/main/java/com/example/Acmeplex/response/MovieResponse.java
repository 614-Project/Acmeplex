package com.example.Acmeplex.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private Integer id;
    private String title;
    private String description;
    private Integer duration;
    private String genre;
    private String movieType;
    private String releaseDate;
    private Double rating;
    private String director;
    private String url;
    private String trailerUrl;
}

