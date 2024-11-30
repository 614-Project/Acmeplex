package com.example.Acmeplex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.TicketResponse;
import com.example.Acmeplex.services.TicketService;


@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

	// @PostMapping("/book")
	// public ResponseEntity<Object> ticketBooking(@RequestBody TicketRequest ticketRequest) {
	// 	try {
	// 		TicketResponse result = ticketService.ticketBooking(ticketRequest);
	// 		return new ResponseEntity<>(result, HttpStatus.CREATED);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	// 	}
	// }

    @PostMapping("/cancel")
    public void cancelTicket(@RequestParam Long ticketNumber){
        ticketService.cancelTicket(ticketNumber);
    }


}