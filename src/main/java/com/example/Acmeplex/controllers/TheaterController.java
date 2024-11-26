package com.example.Acmeplex.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.entities.Theater;
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
	@GetMapping
	public ResponseEntity<List<Theater>> getAllTheaters() {
		List<Theater> theaters = theaterService.getAllTheaters();
		return ResponseEntity.ok(theaters);
	}

	// Endpoint to get a theater by name
	@GetMapping("/{name}")
	public ResponseEntity<Theater> getTheaterByName(@PathVariable String name) {
		Optional<Theater> theater = theaterService.getTheaterByName(name);
		if (theater.isPresent()) {
			return ResponseEntity.ok(theater.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint to update a theater by name
	@PutMapping("/{name}")
	public ResponseEntity<String> updateTheaterByName(@PathVariable String name,
			@RequestBody TheaterRequest theaterRequest) {
		try {
			String response = theaterService.updateTheaterByName(name, theaterRequest);
			return ResponseEntity.ok(response);
		} catch (TheaterDoesNotExist ex) {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint to delete a theater by name
	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteTheaterByName(@PathVariable String name) {
		try {
			String response = theaterService.deleteTheaterByName(name);
			return ResponseEntity.ok(response);
		} catch (TheaterDoesNotExist ex) {
			return ResponseEntity.notFound().build();
		}
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

}