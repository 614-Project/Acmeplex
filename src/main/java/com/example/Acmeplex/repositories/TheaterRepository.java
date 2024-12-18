package com.example.Acmeplex.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.Theater;


@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);

    Optional<Theater> findByNameIgnoreCase(String name);

    @Query("select t from Theater t JOIN Show st ON st.theater.theaterId = t.theaterId where st.movie.movieId = :movieId group by t.theaterId")
    List<Theater> findAllByMovieid(Integer movieId);

    @Query("SELECT ss.seatNo, ss.isAvailable  FROM ShowSeat ss WHERE ss.show.showId = :showId")
    List<ShowSeat> findSeatsByShowId(Integer showId);

}