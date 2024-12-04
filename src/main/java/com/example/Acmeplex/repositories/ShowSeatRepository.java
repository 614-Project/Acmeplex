package com.example.Acmeplex.repositories;

import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.TheaterSeat;
import com.example.Acmeplex.response.TheatreSeatStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat> findByShowShowId(Integer showId);

    @Query(value = "SELECT ts.id, ts.seat_no as seatNo," +
            "CASE WHEN sts.theatre_seat_fk IS NOT NULL THEN 'TAKEN' ELSE 'AVAILABLE' END AS seatStatus " +
            "FROM  theater_seats ts " +
            "LEFT JOIN  show_seats sts ON ts.id = sts.theatre_seat_fk AND sts.show_show_id = :showtimeId " +
            "WHERE ts.theater_theater_id = :theatreId", nativeQuery = true)
    List<TheatreSeatStatusDTO> fetchSeatDistributionForShowtime(@Param("theatreId") Long theatreId, @Param("showtimeId") Long showtimeId);
}