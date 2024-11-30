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

    // This method finds and returns a list of shows (List<Show>) based on a given
    // movieId and theaterId.
    @Query("SELECT s FROM Show s WHERE s.movie.movieId = :movieId AND s.theater.theaterId = :theaterId")
    List<Show> findShowsByMovieAndTheater(@Param("movieId") Integer movieId, @Param("theaterId") Integer theaterId);

    // This method retrieves show timings (List<Time>) for a specific date, movieId,
    // and theaterId.
    @Query("SELECT s.time FROM Show s WHERE s.date = :date AND s.movie.movieId = :movieId AND s.theater.theaterId = :theaterId")
    List<Time> getShowTimingsOnDate(@Param("date") Date date, @Param("theaterId") Integer theaterId,
            @Param("movieId") Integer movieId);

    // This method returns the movieId of the movie that has the most shows
    @Query("SELECT s.movie.movieId FROM Show s GROUP BY s.movie.movieId ORDER BY COUNT(s) DESC")
    Integer getMostShowsMovie();

    // This method finds and returns a list of shows (List<Show>) associated with a
    // given movieId.
    List<Show> findByMovieMovieId(Integer movieId);
}
