package com.example.Acmeplex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.repositiories.TicketRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.StripeResponse;
import com.example.Acmeplex.services.TicketService;


@RestController
@RequestMapping("/product")
public class TicketCheckoutController {

    private TicketService stripeService;

    @Autowired
    TicketRepository productRepository;
    
    public TicketCheckoutController(TicketService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody TicketRequest productRequest) {
        StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }


    @GetMapping("/success")
    public String successPage() {
    return "success"; // Or the actual view name
}
}