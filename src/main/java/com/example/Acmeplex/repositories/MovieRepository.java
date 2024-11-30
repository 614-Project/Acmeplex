package com.example.Acmeplex.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.enums.Genre;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // JpaRepository provides basic and pagination methods
    List<Movie> findByTitleContainingIgnoreCase(String titleCRUD);

    // Method to find movies by genre with pagination
    Page<Movie> findByGenre(Genre genre, Pageable pageable);

    // Method to find movies with a rating greater than or equal to a specified
    // value with pagination
    Page<Movie> findByRatingGreaterThanEqual(Double rating, Pageable pageable);

}
