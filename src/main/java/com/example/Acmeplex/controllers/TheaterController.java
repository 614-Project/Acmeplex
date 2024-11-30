package com.example.Acmeplex.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.Theater;
import com.example.Acmeplex.entities.TheaterSeat;
import com.example.Acmeplex.exceptions.TheaterDoesNotExist;
import com.example.Acmeplex.request.TheaterRequest;
import com.example.Acmeplex.request.TheaterSeatRequest;
import com.example.Acmeplex.services.TheaterService;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	// adds new theater to the database
	@PostMapping("/addNew")
	public ResponseEntity<String> addTheater(@RequestBody TheaterRequest request) {
		try {
			String result = theaterService.addTheater(request);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Endpoint to get all theaters
	// @GetMapping
	// public ResponseEntity<List<Theater>> getAllTheaters() {
	// List<Theater> theaters = theaterService.getAllTheaters();
	// return ResponseEntity.ok(theaters);
	// }

	// endpoint to get list of movies in a theater
	@GetMapping("/movies/{movieId}")
	public ResponseEntity<List<Theater>> findMoviesByTheaterIdAndMovieId(@PathVariable Integer movieId) {
		List<Theater> movies = theaterService.findAllByMovieId(movieId);
		return ResponseEntity.ok(movies);
	}

	@PostMapping("/addTheaterSeat")
	public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatRequest entryDto) {
		try {
			String result = theaterService.addTheaterSeat(entryDto);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// endpoint to show seats available for a show
	@GetMapping("/{theaterId}/seats")
	public ResponseEntity<List<ShowSeat>> getSeatsByShowId(@PathVariable Integer showId) {
		List<ShowSeat> seats = theaterService.getSeatsByShowId(showId);
		return ResponseEntity.ok(seats);
	}

	// @PostMapping("/select-seat/{seatId}")
	// public ResponseEntity<TheaterSeat> selectSeat(@PathVariable Integer seatId) {
	// TheaterSeat selectedSeat = theaterService.selectSeat(seatId);
	// return ResponseEntity.ok(selectedSeat);
	// }

}