package com.example.Acmeplex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.repositiories.TicketRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.StripeResponse;
import com.example.Acmeplex.services.TicketService;


@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;
    
    public TicketController(TicketService stripeService) {
        this.ticketService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody TicketRequest ticketRequest) {
        StripeResponse stripeResponse = ticketService.checkoutProducts(ticketRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

    @PostMapping("/cancel")
    public void cancelTicket(@RequestParam Long ticketNumber){
        ticketService.cancelTicket(ticketNumber);
    }

    @GetMapping("/success")
    public String successPage() {
    return "success"; // Or the actual view name
}
}