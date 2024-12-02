package com.example.Acmeplex.controllers;

import com.example.Acmeplex.convertors.MovieConvertor;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.response.MovieResponse;
import com.example.Acmeplex.services.EmailService;
import com.example.Acmeplex.services.MovieService;
import com.example.Acmeplex.exceptions.MovieNotFoundException;
import com.example.Acmeplex.repositories.UserRepository;
import com.example.Acmeplex.request.MovieRequest;
import com.example.Acmeplex.enums.Genre;

import java.util.List;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addNew")
    public ResponseEntity<MovieResponse> addMovie(@Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = MovieConvertor.toMovie(movieRequest);
        Movie savedMovie = movieService.addMovie(movie);
        MovieResponse movieResponse = MovieConvertor.toMovieResponse(savedMovie);
        
        List<User> users = userRepository.findAll();  // Fetch all users

        for (User user : users) {
            try {
                emailService.sendMovieAddedEmail(user.getEmail(), movie.getTitle());
            } catch (MailException  e) {
                e.printStackTrace();  // Handle error (e.g., log it)
            }
        }
        //emailService.sendMovieAddedEmail( email,  movieName);

        return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);
    }

    // Update an existing movie
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable Integer id,
            @RequestBody MovieRequest movieRequest) {
        Movie movie = movieService.updateMovie(id, movieRequest);
        MovieResponse movieResponse = MovieConvertor.toMovieResponse(movie);
        return new ResponseEntity<>(movieResponse, HttpStatus.OK);
    }

    // Delete a movie by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Integer id) {
        try {
            Movie movie = movieService.getMovieById(id);
            MovieResponse movieResponse = MovieConvertor.toMovieResponse(movie);
            return new ResponseEntity<>(movieResponse, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<MovieResponse>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        Page<Movie> movies = movieService.getMoviesWithPagination(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy)));
        Page<MovieResponse> responses = movies.map(MovieConvertor::toMovieResponse);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<Page<MovieResponse>> getMoviesByGenre(
            @RequestParam Genre genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        Page<Movie> movies = movieService.getMoviesByGenre(genre,
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy)));
        Page<MovieResponse> responses = movies.map(MovieConvertor::toMovieResponse);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/rating")
    public ResponseEntity<Page<MovieResponse>> getMoviesWithMinimumRating(
            @RequestParam Double minRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        Page<Movie> movies = movieService.getMoviesWithMinimumRating(minRating,
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy)));
        Page<MovieResponse> responses = movies.map(MovieConvertor::toMovieResponse);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponse>> findMoviesByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Movie> moviesPage = movieService.findMoviesByTitle(title, pageRequest);
        Page<MovieResponse> responsesPage = moviesPage.map(MovieConvertor::toMovieResponse);
        return ResponseEntity.ok(responsesPage);
    }
}
