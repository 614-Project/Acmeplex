package com.example.Acmeplex.services;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.entities.Credit;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.repositiories.CreditRepository;
import com.example.Acmeplex.repositiories.TicketRepository;
import com.example.Acmeplex.repositiories.UserRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${stripe.api.secret-key}")
    private String secretKey;

    public StripeResponse checkoutProducts(TicketRequest ticketRequest) {
        // Set your secret key. Remember to switch to your live secret key in production!
        Stripe.apiKey = secretKey;

        SessionCreateParams.LineItem.PriceData.ProductData productData =
        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(ticketRequest.getMovieName())
                .build();

        // Create price data, including currency
        SessionCreateParams.LineItem.PriceData priceData =
        SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(ticketRequest.getCurrency() != null ? ticketRequest.getCurrency() : "USD") // Ensure currency is set
                .setUnitAmount(ticketRequest.getAmount())
                .setProductData(productData)
                .build();

    // Create line item with the above price data
    SessionCreateParams.LineItem lineItem =
        SessionCreateParams.LineItem.builder()
                .setQuantity(ticketRequest.getQuantity())
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

        // Save ticket information to the database
        Ticket ticket = new Ticket();
        ticket.setMovieName(ticketRequest.getMovieName());
        ticket.setCurrency(ticketRequest.getCurrency() != null ? ticketRequest.getCurrency() : "USD");  // Ensure currency is set
        ticket.setQuantity(ticketRequest.getQuantity());
        ticket.setAmount(ticketRequest.getAmount());
        ticket.setSessionId(session.getId());  // This will now safely work
        //ticket.setStatus("PENDING"); // Initial status
        ticketRepository.save(ticket);

        return StripeResponse.builder()
                .status("SUCCESS")
                .message("Payment session created successfully.")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
        }

    // Ticket cancellation
    public void cancelTicket(Long id) {
        // Retrieve the ticket from the repository
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if the ticket status is "PENDING" or if the expiration date is valid
        if ("PENDING".equals(ticket.getStatus()) || ticket.getExpireDate().isAfter(LocalDateTime.now())) {
            // Retrieve the payment associated with the ticket
            Payment payment = ticket.getPayment();
            if (payment == null) {
                throw new RuntimeException("Payment information not found, ticket might be expired for the ticket.");
            }

            // Retrieve the user associated with the payment email (if any)
            User user = userRepository.findByEmail(payment.getCustomerEmail()).orElse(null);

            // Calculate the credit value
            int creditValue = (user != null && user.getEmail().equalsIgnoreCase(payment.getCustomerEmail())) 
                    ? 1000 // User found, email matches
                    : 850; // User not found or email does not match

            // Update user's credit if the user exists
            if (user != null) {
                if(user.getCredit()==null){
                    user.setCredit(0);
                }
                user.setCredit(user.getCredit() + creditValue);
                userRepository.save(user); // Save the updated user
            }

            // Create and save the credit
            Credit credit = new Credit();
            credit.setCredit(creditValue);
            credit.setExpireDate(LocalDateTime.now().plusYears(1));
            credit.setTicket(ticket);
            creditRepository.save(credit);

            // Update the ticket status to canceled
            ticket.setStatus("CANCELED");
            ticketRepository.save(ticket);

            System.out.println("Ticket canceled successfully.");
        } else {
            // Ticket cannot be canceled
            throw new RuntimeException("Ticket cannot be canceled as it is not pending or has expired.");
        }
    }
}