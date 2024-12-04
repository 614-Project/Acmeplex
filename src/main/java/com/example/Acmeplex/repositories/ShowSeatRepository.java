package com.example.Acmeplex.repositories;

import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat> findByShowShowId(Integer showId);

    @Query(value = "SELECT ts.id, ts.seatno as seatNo," +
            "CASE WHEN sts.theatre_seat_fk IS NOT NULL THEN 'TAKEN' ELSE 'AVAILABLE' END AS seatStatus " +
            "FROM  theater_seats ts " +
            "LEFT JOIN  show_seats sts ON ts.id = sts.theatre_seat_fk AND sts.show = :showtimeId " +
            "WHERE ts.theatre_fk = :theatreId", nativeQuery = true)
    List<TheaterSeat> fetchSeatDistributionForShowtime(@Param("theatreId") Long theatreId, @Param("showtimeId") Long showtimeId);
}