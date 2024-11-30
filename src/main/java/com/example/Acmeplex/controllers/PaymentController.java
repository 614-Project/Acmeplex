// package com.example.Acmeplex.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.Acmeplex.request.TicketRequest;
// import com.example.Acmeplex.response.TicketResponse;
// import com.example.Acmeplex.services.TicketService;

// @RestController
// @RequestMapping("/ticket")
// public class TicketController {

// @Autowired
// private TicketService ticketService;

// @PostMapping("/book")
// public ResponseEntity<Object> ticketBooking(@RequestBody TicketRequest
// ticketRequest) {
// try {
// TicketResponse result = ticketService.ticketBooking(ticketRequest);
// return new ResponseEntity<>(result, HttpStatus.CREATED);
// } catch (Exception e) {
// return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
// }
// }
// }

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

import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.StripeResponse;
import com.example.Acmeplex.services.PaymentService;
import com.example.Acmeplex.services.TicketService;


@RestController
@RequestMapping("/ticket")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TicketRepository ticketRepository;
    

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody TicketRequest ticketRequest) {
        StripeResponse stripeResponse = paymentService.checkoutProducts(ticketRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

    @GetMapping("/success")
    public String successPage() {
    return "success"; // Or the actual view name
}
}