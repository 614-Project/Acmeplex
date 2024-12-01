package com.example.Acmeplex.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.convertors.TheaterConvertor;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.Theater;
import com.example.Acmeplex.entities.TheaterSeat;
import com.example.Acmeplex.exceptions.SeatsNotAvailable;
import com.example.Acmeplex.exceptions.TheaterAlreadyExists;
import com.example.Acmeplex.exceptions.TheaterDoesNotExist;
import com.example.Acmeplex.repositories.TheaterRepository;
import com.example.Acmeplex.request.TheaterRequest;
import com.example.Acmeplex.request.TheaterSeatRequest;

import jakarta.transaction.Transactional;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	// method to add theater data to the database
	@Transactional
	public String addTheater(TheaterRequest theaterRequest) throws TheaterAlreadyExists {
		if (theaterRepository.findByAddress(theaterRequest.getAddress()) != null) {
			throw new TheaterAlreadyExists();
		}

		Theater theater = TheaterConvertor.theaterDtoToTheater(theaterRequest);

		theaterRepository.save(theater);
		return "Theater has been saved Successfully";
	}

	public List<Theater> findAllByMovieId(Integer movieId) {
		return theaterRepository.findAllByMovieid(movieId);

	}

	// method to add theatreseats to the database
	@Transactional
	public String addTheaterSeat(TheaterSeatRequest entryDto) throws TheaterDoesNotExist {
		if (theaterRepository.findByAddress(entryDto.getAddress()) == null) {
			throw new TheaterDoesNotExist();
		}

		Integer numberOfRows = entryDto.getNumberOfRows();
		Integer numberOfSeatsPerRow = entryDto.getNumberOfSeatPerRow();
		String address = entryDto.getAddress();

		Theater theater = theaterRepository.findByAddress(address);

		List<TheaterSeat> seatList = theater.getTheaterSeatList();

		int rowCounter = 1;
		int seatCounter = 1;
		int totalSeats = numberOfSeatsPerRow * numberOfRows;

		for (int i = 1; i <= totalSeats; i++) {
			String seatNo = "R" + rowCounter + "-S" + seatCounter;

			TheaterSeat theaterSeat = new TheaterSeat();
			theaterSeat.setSeatNo(seatNo);
			theaterSeat.setTheater(theater);
			seatList.add(theaterSeat);

			seatCounter++;
			if (seatCounter > numberOfSeatsPerRow) {
				seatCounter = 1;
				rowCounter++;
			}
		}
		theaterRepository.save(theater);
		return "Theater Seats have been added successfully";
	}

	public List<ShowSeat> getSeatsByShowId(Integer showId) {
		return theaterRepository.findSeatsByShowId(showId);

	}

}