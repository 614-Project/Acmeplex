package com.example.Acmeplex.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.request.PaymentRequest;
import com.example.Acmeplex.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService {
    
    @Autowired
    private TicketRepository ticketRepository;

     // Stripe secret key
    @Value("${stripe.api.secret-key}")
    private String secretKey;

    // checkout the ticket
    public PaymentResponse checkoutProducts(PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;

        Optional<Ticket> ticketopt = ticketRepository.findById(paymentRequest.getTicketId());

        // If ticket does not exist on database, return a failure response
        if (ticketopt.isEmpty()) {
            return PaymentResponse.builder()
                    .status("FAILED")
                    .message("Ticket with the given ID does not exist.")
                    .build();
        }
        Ticket ticket = ticketopt.get();
        Show show = ticket.getShow();
        Movie movie = show.getMovie();
        String movieName = movie.getTitle();

        // Retrieve the amount from the ticket (this assumes the ticket has a price associated with it)
        Long ticketAmount = ticket.getTotalTicketsPrice();  
        
        // check if this ticket is already paid, if so throw an error
        if (ticket.getStatus().equals("PAID")) {
            return PaymentResponse.builder()
                    .status("FAILED")
                    .message("Ticket is alreaady paid")
                    .build();
        }
        SessionCreateParams.LineItem.PriceData.ProductData productData =
        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(movieName)
                .build();

        // Create price data, including currency
        SessionCreateParams.LineItem.PriceData priceData =
        SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(paymentRequest.getCurrency() != null ? paymentRequest.getCurrency() : "USD")
                .setUnitAmount(ticketAmount)
                .setProductData(productData)
                .build();

    // Create line item with the above price data
    SessionCreateParams.LineItem lineItem =
        SessionCreateParams.LineItem.builder()
                .setQuantity(paymentRequest.getQuantity())
                .setPriceData(priceData)
                .build();

    // Create session with the line item
    SessionCreateParams params =
        SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(lineItem)
                .build();

        // Create session and handle possible errors
        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
            return PaymentResponse.builder()
                    .status("FAILED")
                    .message("Error occurred while creating Stripe session: " + e.getMessage())
                    .build();
        }

        if (session == null) {
            // If session is still null, return a failure response
            return PaymentResponse.builder()
                    .status("FAILED")
                    .message("Failed to create Stripe session. Please try again.")
                    .build();
        }

        //update ticket status
        ticket.setSessionId(session.getId());  
        ticket.setStatus("PAID"); 

        Payment payment = new Payment();
        payment.setTicket(ticket);

        ticketRepository.save(ticket);

        return PaymentResponse.builder()
                .status("SUCCESS")
                .message("Payment session created successfully.")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
        }

     
}
