package com.example.Acmeplex.convertors;

import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.request.MovieRequest;
import com.example.Acmeplex.response.MovieResponse;

public class MovieConvertor {

      /*
     * This method helps to convert a movie request to a movie object(entity)
     * before saving the data to the database.
     */
  public static MovieResponse toMovieResponse(Movie movie) {
    return MovieResponse.builder()
        .id(movie.getMovieId())
        .title(movie.getTitle())
        .description(movie.getDescription())
        .duration(movie.getDuration())
        .genre(movie.getGenre().toString())
        .releaseDate(movie.getReleaseDate().toString())
        .rating(movie.getRating())
        .director(movie.getDirector())
        .bannerUrl(movie.getBannerUrl())
        .carouselUrl(movie.getCarouselUrl())
        .trailerUrl(movie.getTrailerUrl())
        .build();
  }

  public static Movie toMovie(MovieRequest movieRequest) {
    return Movie.builder()
        .title(movieRequest.getTitle())
        .description(movieRequest.getDescription())
        .duration(movieRequest.getDuration())
        .genre(movieRequest.getGenre())
        .releaseDate(movieRequest.getReleaseDate())
        .rating(movieRequest.getRating())
        .director(movieRequest.getDirector())
        .cast(movieRequest.getCast())
        .bannerUrl(movieRequest.getBannerUrl())
        .carouselUrl(movieRequest.getCarouselUrl())
        .trailerUrl(movieRequest.getTrailerUrl())
        .build();
  }
}
