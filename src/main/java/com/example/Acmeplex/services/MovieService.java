package com.example.Acmeplex.services;

import com.example.Acmeplex.exceptions.MovieNotFoundException;
import com.example.Acmeplex.request.MovieRequest;
import com.example.Acmeplex.repositories.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.enums.Genre;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Add a new movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Update an existing movie
    public Movie updateMovie(Integer id, MovieRequest movieRequest) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));

        // Update only non-null fields
        if (movieRequest.getTitle() != null)
            existingMovie.setTitle(movieRequest.getTitle());
        if (movieRequest.getDescription() != null)
            existingMovie.setDescription(movieRequest.getDescription());
        if (movieRequest.getDuration() != null)
            existingMovie.setDuration(movieRequest.getDuration());
        if (movieRequest.getGenre() != null)
            existingMovie.setGenre(movieRequest.getGenre());
        if (movieRequest.getMovieType() != null)
            existingMovie.setMovieType((movieRequest.getMovieType()));
        if (movieRequest.getReleaseDate() != null)
            existingMovie.setReleaseDate(movieRequest.getReleaseDate());
        if (movieRequest.getRating() != null)
            existingMovie.setRating(movieRequest.getRating());
        if (movieRequest.getDirector() != null)
            existingMovie.setDirector(movieRequest.getDirector());
        if (movieRequest.getCast() != null)
            existingMovie.setCast(movieRequest.getCast());
        if (movieRequest.getUrl() != null)
            existingMovie.setUrl(movieRequest.getUrl());
        if (movieRequest.getTrailerUrl() != null)
            existingMovie.setTrailerUrl(movieRequest.getTrailerUrl());

        return movieRepository.save(existingMovie);
    }

    // Delete a movie by ID
    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
        movieRepository.delete(movie);
    }

    public Page<Movie> getMoviesWithPagination(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Page<Movie> getMoviesByGenre(Genre genre, Pageable pageable) {
        return movieRepository.findByGenre(genre, pageable);
    }

    public Page<Movie> getMoviesWithMinimumRating(Double rating, Pageable pageable) {
        return movieRepository.findByRatingGreaterThanEqual(rating, pageable);
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
    }

    public List<Movie> findMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies found with title: " + title);
        }
        return movies;
    }
}
