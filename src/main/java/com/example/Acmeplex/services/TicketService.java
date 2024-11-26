package com.example.Acmeplex.services;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.repositiories.PaymentRepository;
import com.example.Acmeplex.repositiories.TicketRepository;
import com.example.Acmeplex.response.StripeResponse;

// import jakarta.transaction.Transactional;
// @Service
// @Transactional
// public class PaymentService {

//     private PaymentRepository paymentRepository;
//     private static final String secretKey = "sk_test_51QNrf6EN5RfB81n8y68FX08ZqjxpMAKcVORyttoRodYPeVWZfwjoogDhSOWnAKEfZW4DamJeoWlsJgWJgRbbCVcD00haA3eZPg";

//     @Autowired
//     public PaymentService(PaymentRepository paymentRepository) {
//         this.paymentRepository = paymentRepository;
//         Stripe.apiKey = secretKey;
//     }

//     @Transactional
//     public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
//         List<String> paymentMethodTypes = new ArrayList<>();
//         paymentMethodTypes.add("card");

//         Map<String, Object> params = new HashMap<>();
//         params.put("amount", paymentInfoRequest.getAmount());
//         params.put("currency", paymentInfoRequest.getCurrency());
//        // params.put("email", paymentInfoRequest.getEmail());
//         params.put("payment_method_types", paymentMethodTypes);

//         return PaymentIntent.create(params); 
//         // Save PaymentIntent details to the database 
//         // PaymentIntentEntity paymentIntentEntity = new PaymentIntentEntity(); 
//         // paymentIntentEntity.setPaymentIntentId(paymentIntent.getId()); 
//         // paymentIntentEntity.setClientSecret(paymentIntent.getClientSecret()); 
//         // paymentIntentEntity.setAmount(paymentIntent.getAmount() / 100.0); 
//         // // Convert cents to dollars 
//         // paymentIntentEntity.setEmail(paymentIntent.getReceiptEmail()); 
//         // paymentIntentEntity.setCurrency(paymentIntent.getCurrency()); 
//         // paymentIntentEntity.setStatus(paymentIntent.getStatus()); 
//         // paymentRepository.save(paymentIntentEntity); 
        
//         //return paymentIntent; 
//     } 

//     public ResponseEntity<String> stripePayment(String userEmail) throws Exception {
//         PaymentIntentEntity payment = paymentRepository.findByEmail(userEmail);

//         if (payment == null) {
//             throw new Exception("Payment information is missing");
//         }
//         payment.setAmount(00.00);
//         paymentRepository.save(payment);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

// }


// import com.example.Acmeplex.request.TicketRequest;
// import com.example.Acmeplex.response.StripeResponse;
// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.checkout.Session;

// import com.stripe.param.checkout.SessionCreateParams;

// import jakarta.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;


// @Service
// @Transactional
// public class TicketService{

//     @Autowired
//     private TicketRepository ticketRepository;
//     private static final String secretKey = "sk_test_51QNrf6EN5RfB81n8y68FX08ZqjxpMAKcVORyttoRodYPeVWZfwjoogDhSOWnAKEfZW4DamJeoWlsJgWJgRbbCVcD00haA3eZPg";

//     public StripeResponse checkoutProducts(TicketRequest ticketRequest) {
//         // Set your secret key. Remember to switch to your live secret key in production!
//         Stripe.apiKey = secretKey;

//         // Create a PaymentIntent with the order amount and currency
//         SessionCreateParams.LineItem.PriceData.ProductData productData =
//                 SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                         .setName(ticketRequest.getMovieName())
                        
//                         .build();

//         // Create new line item with the above product data and associated price
//         SessionCreateParams.LineItem.PriceData priceData =
//                 SessionCreateParams.LineItem.PriceData.builder()
//                         .setCurrency(ticketRequest.getCurrency() != null ? ticketRequest.getCurrency() : "USD")
//                         .setUnitAmount(ticketRequest.getAmount())
//                         .setProductData(Data)
//                         .build();

//         // Create new line item with the above price data
//         SessionCreateParams.LineItem lineItem =
//                 SessionCreateParams
//                         .LineItem.builder()
//                         .setQuantity(ticketRequest.getQuantity())
//                         .setPriceData(priceData)
//                         .build();

//         // Create new session with the line items
//         SessionCreateParams params =
//                 SessionCreateParams.builder()
//                         .setMode(SessionCreateParams.Mode.PAYMENT)
//                         .setSuccessUrl("http://localhost:8080/success")
//                         .setCancelUrl("http://localhost:8080/cancel")
//                         .addLineItem(lineItem)
//                         .build();

//         // Create new session
//         Session session = null;
//         try {
//             session = Session.create(params);
//         } catch (StripeException e) {
//             //log the error
//         }


//         Ticket product = new Ticket();
//         product.setProduct_name(productRequest.getName());
//         product.setCurrency(productRequest.getCurrency());
//         product.setQuantity(productRequest.getQuantity());
//         product.setAmount(productRequest.getAmount());
//         product.setSessionId(session.getId());
//         product.setStatus("PENDING"); // Initial status
//         productRepository.save(product);


//         return StripeResponse.builder()
//                 .status("SUCCESS")
//                 .message("Payment session created ")
//                 .sessionId(session.getId())
//                 .sessionUrl(session.getUrl())
//                 .build();
//     }

// }


import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.repositiories.TicketRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private static final String secretKey = "sk_test_51QNrf6EN5RfB81n8y68FX08ZqjxpMAKcVORyttoRodYPeVWZfwjoogDhSOWnAKEfZW4DamJeoWlsJgWJgRbbCVcD00haA3eZPg";

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


    // Create and save Payment
//     Payment payment = new Payment();
//     payment.setStatus("PENDING");
//     payment.setTicket(ticket); // Link the ticket to the payment
//     paymentRepository.save(payment);

// Return response with the session info
return StripeResponse.builder()
        .status("SUCCESS")
        .message("Payment session created successfully.")
        .sessionId(session.getId())
        .sessionUrl(session.getUrl())
        .build();
}
}