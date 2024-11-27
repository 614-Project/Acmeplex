package com.example.Acmeplex.convertor;

import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.request.MovieRequest;
import com.example.Acmeplex.response.MovieResponse;

public class MovieConvertor {

  public static MovieResponse toMovieResponse(Movie movie) {
    return MovieResponse.builder()
        .id(movie.getMovieId())
        .title(movie.getTitle())
        .description(movie.getDescription())
        .duration(movie.getDuration())
        .genre(movie.getGenre().toString())
        .movieType(movie.getMovieType() != null ? movie.getMovieType().toString() : null) // Handle null MovieType
        .releaseDate(movie.getReleaseDate().toString())
        .rating(movie.getRating())
        .director(movie.getDirector())
        .url(movie.getUrl())
        .trailerUrl(movie.getTrailerUrl())
        .build();
  }

  public static Movie toMovie(MovieRequest movieRequest) {
    return Movie.builder()
            .title(movieRequest.getTitle())
            .description(movieRequest.getDescription())
            .duration(movieRequest.getDuration())
            .genre(movieRequest.getGenre())
            .movieType(movieRequest.getMovieType())
            .releaseDate(movieRequest.getReleaseDate())
            .rating(movieRequest.getRating())
            .director(movieRequest.getDirector())
            .cast(movieRequest.getCast())
            .url(movieRequest.getUrl())
            .trailerUrl(movieRequest.getTrailerUrl())
            .build();
  }
}
