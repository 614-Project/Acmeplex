package com.example.Acmeplex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.convertors.TheaterConvertor;
import com.example.Acmeplex.entities.Theater;
// import com.example.Acmeplex.entities.TheaterSeat;
// import com.example.Acmeplex.enums.SeatType;
import com.example.Acmeplex.exceptions.TheaterDoesNotExist;
import com.example.Acmeplex.exceptions.TheaterAlreadyExists;
import com.example.Acmeplex.repositories.TheaterRepository;
import com.example.Acmeplex.request.TheaterRequest;
// import com.example.Acmeplex.request.TheaterSeatRequest;

import java.util.List;

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
	// // method to add theatreseats to the database
	// public String addTheaterSeat(TheaterSeatRequest entryDto) throws
	// TheaterDoesNotExist {
	// if (theaterRepository.findByAddress(entryDto.getAddress()) == null) {
	// throw new TheaterDoesNotExist();
	// }

	// Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
	// Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
	// Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
	// String address = entryDto.getAddress();

	// Theater theater = theaterRepository.findByAddress(address);

	// List<TheaterSeat> seatList = theater.getTheaterSeatList();

	// int counter = 1;
	// int fill = 0;
	// char ch = 'A';

	// for (int i = 1; i <= noOfClassicSeat; i++) {
	// String seatNo = Integer.toString(counter) + ch;

	// ch++;
	// fill++;
	// if (fill == noOfSeatsInRow) {
	// fill = 0;
	// counter++;
	// ch = 'A';
	// }

	// TheaterSeat theaterSeat = new TheaterSeat();
	// theaterSeat.setSeatNo(seatNo);
	// theaterSeat.setSeatType(SeatType.CLASSIC);
	// theaterSeat.setTheater(theater);
	// seatList.add(theaterSeat);
	// }

	// for (int i = 1; i <= noOfPremiumSeats; i++) {
	// String seatNo = Integer.toString(counter) + ch;

	// ch++;
	// fill++;
	// if (fill == noOfSeatsInRow) {
	// fill = 0;
	// counter++;
	// ch = 'A';
	// }

	// TheaterSeat theaterSeat = new TheaterSeat();
	// theaterSeat.setSeatNo(seatNo);
	// theaterSeat.setSeatType(SeatType.PREMIUM);
	// theaterSeat.setTheater(theater);
	// seatList.add(theaterSeat);
	// }

	// theaterRepository.save(theater);

	// return "Theater Seats have been added successfully";
	// }
}