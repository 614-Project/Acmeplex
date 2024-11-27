package com.example.Acmeplex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.convertors.TheaterConvertor;
import com.example.Acmeplex.entities.Theater;
import com.example.Acmeplex.entities.TheaterSeat;
import com.example.Acmeplex.exceptions.TheaterDoesNotExist;
import com.example.Acmeplex.exceptions.TheaterAlreadyExists;
import com.example.Acmeplex.repositories.TheaterRepository;
import com.example.Acmeplex.request.TheaterRequest;
import com.example.Acmeplex.request.TheaterSeatRequest;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	// method to add theater data to the database
	public String addTheater(TheaterRequest theaterRequest) throws TheaterAlreadyExists {
		if (theaterRepository.findByAddress(theaterRequest.getAddress()) != null) {
			throw new TheaterAlreadyExists();
		}

		Theater theater = TheaterConvertor.theaterDtoToTheater(theaterRequest);

		theaterRepository.save(theater);
		return "Theater has been saved Successfully";
	}

	// Method to get all theaters
	public List<Theater> getAllTheaters() {
		return theaterRepository.findAll();
	}

	// Method to get a theater by ID
	public Optional<Theater> getTheaterById(Integer id) {
		return theaterRepository.findById(id);
	}

	// method to find a theatre by name
	public Optional<Theater> getTheaterByName(String name) {
		return theaterRepository.findByNameIgnoreCase(name);
	}

	// Method to update theater data by ID
	public String updateTheaterById(Integer id, TheaterRequest theaterRequest) throws TheaterDoesNotExist {
		Optional<Theater> theaterOpt = theaterRepository.findById(id);

		if (theaterOpt.isEmpty()) {
			throw new TheaterDoesNotExist();
		}

		Theater theater = theaterOpt.get();
		theater.setName(theaterRequest.getName());
		theater.setAddress(theaterRequest.getAddress());
		theaterRepository.save(theater);
		return "Theater has been updated Successfully";
	}

	// method to update theatre by name
	public String updateTheaterByName(String name, TheaterRequest theaterRequest) throws TheaterDoesNotExist {
		Optional<Theater> theaterOpt = theaterRepository.findByNameIgnoreCase(name);

		if (theaterOpt.isEmpty()) {
			throw new TheaterDoesNotExist();
		}

		Theater theater = theaterOpt.get();
		theater.setName(theaterRequest.getName());
		theater.setAddress(theaterRequest.getAddress());
		theaterRepository.save(theater);

		return "Theater has been updated Successfully";

	}

	// method to delete theatre by ID
	public String deleteTheaterById(Integer id) throws TheaterDoesNotExist {
		Optional<Theater> theaterOpt = theaterRepository.findById(id);
		if (theaterOpt.isEmpty()) {
			throw new TheaterDoesNotExist();
		}
		theaterRepository.deleteById(id);
		return "Theater has been deleted Successfully";
	}

	// Method to delete a theater by name
	public String deleteTheaterByName(String name) throws TheaterDoesNotExist {
		Optional<Theater> theaterOpt = theaterRepository.findByNameIgnoreCase(name);
		if (theaterOpt.isEmpty()) {
			throw new TheaterDoesNotExist();
		}
		theaterRepository.delete(theaterOpt.get());
		return "Theater has been deleted Successfully";
	}

	// method to add theatreseats to the database
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

}