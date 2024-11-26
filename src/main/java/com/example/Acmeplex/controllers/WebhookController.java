package com.example.Acmeplex.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.entities.Product;
import com.example.Acmeplex.repositiories.ProductRepository;
import com.example.Acmeplex.services.EmailService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

@RestController
public class WebhookController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;
    
    @Autowired
     ProductRepository productRepository;
    
     @Autowired
    private EmailService emailService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
                                                      @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
            JsonObject eventJson = JsonParser.parseString(payload).getAsJsonObject();
            String eventType = eventJson.get("type").getAsString();

            // Process the event
            if ("checkout.session.completed".equals(eventType)) {
                JsonObject session = eventJson.getAsJsonObject("data").getAsJsonObject("object");
                String sessionId = session.get("id").getAsString();
                String customerEmail = session.get("customer_details").getAsJsonObject().get("email").getAsString();
                String paymentIntentId = session.get("payment_intent").getAsString();
                
                // Log or process the session ID and customer email
                System.out.println("Session ID: " + sessionId);
                System.out.println("Customer Email: " + customerEmail);

             Optional<Product> productOptional = productRepository.findBySessionId(sessionId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                
                product.setStatus("PAID");
                product.setCreatedDate(LocalDateTime.now());
                product.setExpireDate(LocalDateTime.now().plusDays(3));

                if (customerEmail != null) {
                    product.setCustomerEmail(customerEmail);
                }
                productRepository.save(product);
                // Log the saved product details
                System.out.println("Product updated: " + product);

                //Send Email to customer
                //String subject = "Payment Successful";
                String body = "Dear Customer, your payment has been successfully processed. Thank you for your purchase!. Your confirmation number is " + paymentIntentId;
                emailService.sendPaymentSuccessEmail(customerEmail, body);

                return ResponseEntity.ok("Product status updated to PAID");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Product not found for session ID: " + sessionId);
            }

            }

            return ResponseEntity.ok("Webhook received");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
