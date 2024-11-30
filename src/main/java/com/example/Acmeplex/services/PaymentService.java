// package com.example.Acmeplex.services;
// import java.time.LocalDateTime;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.example.Acmeplex.entities.Credit;
// import com.example.Acmeplex.entities.Payment;
// import com.example.Acmeplex.entities.Show;
// import com.example.Acmeplex.entities.Ticket;
// import com.example.Acmeplex.entities.User;
// import com.example.Acmeplex.exceptions.ShowDoesNotExist;
// import com.example.Acmeplex.repositories.CreditRepository;
// import com.example.Acmeplex.repositories.TicketRepository;
// import com.example.Acmeplex.repositories.UserRepository;
// import com.example.Acmeplex.request.PaymentRequest;
// import com.example.Acmeplex.request.TicketRequest;
// import com.example.Acmeplex.response.StripeResponse;
// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.checkout.Session;
// import com.stripe.param.checkout.SessionCreateParams;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class PaymentService {
    
//     @Autowired
//     private TicketRepository ticketRepository;

//     @Autowired
//     private CreditRepository creditRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Value("${stripe.api.secret-key}")
//     private String secretKey;

//     public StripeResponse checkoutProducts(PaymentRequest paymentRequest) {
//         // Set your secret key. Remember to switch to your live secret key in production!
//         Stripe.apiKey = secretKey;

        

//         SessionCreateParams.LineItem.PriceData.ProductData productData =
//         SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                 .setName(paymentRequest.getMovieName())
//                 .build();

//         // Create price data, including currency
//         SessionCreateParams.LineItem.PriceData priceData =
//         SessionCreateParams.LineItem.PriceData.builder()
//                 .setCurrency(paymentRequest.getCurrency() != null ? paymentRequest.getCurrency() : "USD") // Ensure currency is set
//                 .setUnitAmount(paymentRequest.getAmount())
//                 .setProductData(productData)
//                 .build();

//     // Create line item with the above price data
//     SessionCreateParams.LineItem lineItem =
//         SessionCreateParams.LineItem.builder()
//                 .setQuantity(paymentRequest.getQuantity())
//                 .setPriceData(priceData)
//                 .build();

//     // Create session with the line item
//     SessionCreateParams params =
//         SessionCreateParams.builder()
//                 .setMode(SessionCreateParams.Mode.PAYMENT)
//                 .setSuccessUrl("http://localhost:8080/success")
//                 .setCancelUrl("http://localhost:8080/cancel")
//                 .addLineItem(lineItem)
//                 .build();

//         // Create session and handle possible errors
//         Session session = null;
//         try {
//             session = Session.create(params);
//         } catch (StripeException e) {
//             // Handle the exception (e.g., log the error or return a custom error response)
//             e.printStackTrace();
//             return StripeResponse.builder()
//                     .status("FAILED")
//                     .message("Error occurred while creating Stripe session: " + e.getMessage())
//                     .build();
//         }

//         if (session == null) {
//             // If session is still null, return a failure response
//             return StripeResponse.builder()
//                     .status("FAILED")
//                     .message("Failed to create Stripe session. Please try again.")
//                     .build();
//         }

//         Optional<Ticket> ticketopt = ticketRepository.findById(paymentRequest.getTicketId());

// 		if (ticketopt.isEmpty()) {
// 			return null;
// 		}
//         Ticket ticket = ticketopt.get();

//         //ticket.setMovieName(ticketRequest.getMovieName());
//         //ticket.setCurrency(ticketRequest.getCurrency() != null ? ticketRequest.getCurrency() : "USD");  // Ensure currency is set
//         //ticket.setQuantity(ticketRequest.getQuantity());
//         //ticket.setTotalTicketsPrice(ticketRequest.getAmount());
//         ticket.setSessionId(session.getId());  // This will now safely work
//         ticket.setStatus("PENDING"); // Initial status

//         Payment payment = new Payment();
//         payment.setTicket(ticket);

//         ticketRepository.save(ticket);

//         return StripeResponse.builder()
//                 .status("SUCCESS")
//                 .message("Payment session created successfully.")
//                 .sessionId(session.getId())
//                 .sessionUrl(session.getUrl())
//                 .build();
//         }

     
// }


package com.example.Acmeplex.services;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.entities.Credit;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.exceptions.ShowDoesNotExist;
import com.example.Acmeplex.repositories.CreditRepository;
import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.repositories.UserRepository;
import com.example.Acmeplex.request.PaymentRequest;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.StripeResponse;
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

    @Value("${stripe.api.secret-key}")
    private String secretKey;

    public StripeResponse checkoutProducts(PaymentRequest paymentRequest) {
        // Set your secret key. Remember to switch to your live secret key in production!
        Stripe.apiKey = secretKey;

        Optional<Ticket> ticketopt = ticketRepository.findById(paymentRequest.getTicketId());

        // If ticket does not exist, return a failure response
        if (ticketopt.isEmpty()) {
            return StripeResponse.builder()
                    .status("FAILED")
                    .message("Ticket with the given ID does not exist.")
                    .build();
        }
        Ticket ticket = ticketopt.get();
        Show show = ticket.getShow();
        Movie movie = show.getMovie();
        String movieName = movie.getTitle();
        // Retrieve the amount from the ticket (this assumes the ticket has a price associated with it)
        Long ticketAmount = ticket.getTotalTicketsPrice();  // Make sure this field holds the correct price
    
        if (ticket.getStatus().equals("PAID")) {
            return StripeResponse.builder()
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
                .setCurrency(paymentRequest.getCurrency() != null ? paymentRequest.getCurrency() : "USD") // Ensure currency is set
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
            // Handle the exception (e.g., log the error or return a custom error response)
            e.printStackTrace();
            return StripeResponse.builder()
                    .status("FAILED")
                    .message("Error occurred while creating Stripe session: " + e.getMessage())
                    .build();
        }

        if (session == null) {
            // If session is still null, return a failure response
            return StripeResponse.builder()
                    .status("FAILED")
                    .message("Failed to create Stripe session. Please try again.")
                    .build();
        }

        //Optional<Ticket> ticketopt1 = ticketRepository.findById(paymentRequest.getTicketId());

        //Ticket ticket1 = ticketopt1.get();

        //ticket.setMovieName(ticketRequest.getMovieName());
        //ticket.setCurrency(ticketRequest.getCurrency() != null ? ticketRequest.getCurrency() : "USD");  // Ensure currency is set
        //ticket.setQuantity(ticketRequest.getQuantity());
        //ticket.setTotalTicketsPrice(ticketRequest.getAmount());
        ticket.setSessionId(session.getId());  // This will now safely work
        ticket.setStatus("PAID"); // Initial status

        Payment payment = new Payment();
        payment.setTicket(ticket);

        ticketRepository.save(ticket);

        return StripeResponse.builder()
                .status("SUCCESS")
                .message("Payment session created successfully.")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
        }

     
}
