package com.example.Acmeplex.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.repositories.PaymentRepository;
import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.services.EmailService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class WebhookController {

    
    @Value("${stripe.webhook.secret}")
    private String endpointSecret;
    
    @Autowired
     TicketRepository ticketRepository;

     @Autowired
     PaymentRepository paymentRepository;
    
     @Autowired
    private EmailService emailService;

    @GetMapping("/success")
    public RedirectView successPage() {
    return new RedirectView("http://localhost:3000/payment-success");
    }

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

                //get customer details from server
                String customerEmail = session.get("customer_details").getAsJsonObject().get("email").getAsString();
                String customerName = session.get("customer_details").getAsJsonObject().get("name").getAsString();
                String paymentIntentId = session.get("payment_intent").getAsString();

            // check id the ticket present on database or not
             Optional<Ticket> ticketOptional = ticketRepository.findBySessionId(sessionId);
            if (ticketOptional.isPresent()) {
                Ticket ticket = ticketOptional.get();
                Payment payment = new Payment();
                
                //update ticket and customer databse
                ticket.setStatus("PAID");
                ticket.setExpireDate(LocalDateTime.now().plusDays(3));
                payment .setCustomerEmail(customerEmail);
                payment.setCustomerName(customerName);
                payment.setConfirmationId(paymentIntentId);
                payment.setTicket(ticket);

                paymentRepository.save(payment);
                ticketRepository.save(ticket);

                // when the stripe server sends confirmation od payment to our local server than send confirmation of payment and ticket details to customer
                emailService.sendPaymentSuccessEmail(customerEmail, paymentIntentId);
                emailService.sendTicketDetailsEmail( customerEmail, ticket, customerName);
                
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
