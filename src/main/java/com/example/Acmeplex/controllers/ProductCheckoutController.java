package com.example.Acmeplex.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Acmeplex.repositiories.ProductRepository;
import com.example.Acmeplex.request.ProductRequest;
import com.example.Acmeplex.response.StripeResponse;
import com.example.Acmeplex.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductCheckoutController {

    private static final String endpointSecret = "whsec_5a77522b04760d992978cab6144b0d040146bb1f92699e72a634c538081c08fd";

    private ProductService stripeService;
    @Autowired
    ProductRepository productRepository;
    public ProductCheckoutController(ProductService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest) {
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