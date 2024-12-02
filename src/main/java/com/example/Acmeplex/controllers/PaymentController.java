package com.example.Acmeplex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.example.Acmeplex.request.PaymentRequest;
import com.example.Acmeplex.response.PaymentResponse;
import com.example.Acmeplex.services.PaymentService;

@RestController
@RequestMapping("/ticket")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/checkout")
    public ResponseEntity<PaymentResponse> checkoutProducts(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse stripeResponse = paymentService.checkoutProducts(paymentRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

    @GetMapping("/success")
    public RedirectView successPage() {
    return new RedirectView("http://localhost:3000/payment-success"); 
}
}