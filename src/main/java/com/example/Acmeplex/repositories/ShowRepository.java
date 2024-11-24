package com.example.Acmeplex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Acmeplex.entities.Show;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query("SELECT s FROM Show s WHERE s.movie.movieId = :movieId AND s.theater.theaterId = :theaterId")
    List<Show> findShowsByMovieAndTheater(@Param("movieId") Integer movieId, @Param("theaterId") Integer theaterId);

    // @Query("SELECT s.time FROM Show s WHERE s.date = :date AND s.movie.movieId =
    // :movieId AND s.theater.theaterId = :theaterId")
    // List<Time> getShowTimingsOnDate(@Param("date") Date date, @Param("theaterId")
    // Integer theaterId,
    // @Param("movieId") Integer movieId);

    // @Query("SELECT s.movie.movieId FROM Show s GROUP BY s.movie.movieId ORDER BY
    // COUNT(s) DESC")
    // Integer getMostShowsMovie();

    // List<Show> findByMovieMovieId(Integer movieId); // Derived query for
    // simplicity.

    // @Query(value = "select time from shows where date = :date and movie_id =
    // :movieId and theater_id = :theaterId", nativeQuery = true)
    // public List<Time> getShowTimingsOnDate(@Param("date") Date date,
    // @Param("theaterId") Integer theaterId,
    // @Param("movieId") Integer movieId);

    // @Query(value = "select movie_id from shows group by movie_id order by
    // count(*) desc limit 1", nativeQuery = true)
    // public Integer getMostShowsMovie();

    // @Query(value = "select * from shows where movie_id = :movieId", nativeQuery =
    // true)
    // public List<Show> getAllShowsOfMovie(@Param("movieId") Integer movieId);
}
