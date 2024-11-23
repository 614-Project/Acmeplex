// package com.example.Acmeplex.services;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import com.example.Acmeplex.config.JWTService;
// import com.example.Acmeplex.entities.Payment;
// import com.example.Acmeplex.entities.User;
// import com.example.Acmeplex.repositiories.PaymentRepository;
// import com.example.Acmeplex.repositiories.UserRepository;
// import com.example.Acmeplex.request.PaymentInfoRequest;
// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.PaymentIntent;
// import com.stripe.param.PaymentIntentCreateParams;

// @Service
// public class PaymentService {

//     private final PaymentRepository paymentRepository;
//     private final UserRepository userRepository;
//     private final JWTService jwtService;

//     // @Value("${stripe.api.key}")
//     // private String secretKey;

//     @Autowired
//     public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, JWTService jwtService) {
//         this.paymentRepository = paymentRepository;
//         this.userRepository = userRepository;
//         this.jwtService = jwtService;
//         Stripe.apiKey = "sk_test_51QNrf6EN5RfB81n8y68FX08ZqjxpMAKcVORyttoRodYPeVWZfwjoogDhSOWnAKEfZW4DamJeoWlsJgWJgRbbCVcD00haA3eZPg";
//     }

//     public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
//         PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//             .setAmount((long) paymentInfoRequest.getAmount() )  // Stripe expects amount in cents
//             .setCurrency(paymentInfoRequest.getCurrency())
//             .build();

//         paymentRepository.save(params);
//         return PaymentIntent.create(params);
//     }

//     public ResponseEntity<String> stripePayment(String token) throws Exception {
//         String userEmail = jwtService.extractUsername(token);
//         if (userEmail == null) {
//             throw new Exception("User email is missing");
//         }

//         Optional<User> userOptional = userRepository.findByEmail(userEmail);
//         if (!userOptional.isPresent()) {
//             throw new Exception("User not found");
//         }

//         //User user = userOptional.get();

//         // Save the payment details in the database
//         Payment payment = new Payment();
//         payment.setAmount(1000); // Example amount, replace with actual logic
//         //payment.setUser(user);
//         paymentRepository.save(payment);

//         return ResponseEntity.ok("Payment completed successfully for user: " + userEmail);
//     }
// }

package com.example.Acmeplex.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Acmeplex.entities.PaymentIntentEntity;
import com.example.Acmeplex.repositiories.PaymentRepository;
import com.example.Acmeplex.request.PaymentInfoRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepository;
    private static final String secretKey = "sk_test_51QNrf6EN5RfB81n8y68FX08ZqjxpMAKcVORyttoRodYPeVWZfwjoogDhSOWnAKEfZW4DamJeoWlsJgWJgRbbCVcD00haA3eZPg";

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        Stripe.apiKey = secretKey;
    }

    @Transactional
    public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfoRequest.getAmount());
        params.put("currency", paymentInfoRequest.getCurrency());
       // params.put("email", paymentInfoRequest.getEmail());
        params.put("payment_method_types", paymentMethodTypes);

        PaymentIntent paymentIntent = PaymentIntent.create(params); 
        // Save PaymentIntent details to the database 
        PaymentIntentEntity paymentIntentEntity = new PaymentIntentEntity(); 
        paymentIntentEntity.setPaymentIntentId(paymentIntent.getId()); 
        paymentIntentEntity.setClientSecret(paymentIntent.getClientSecret()); 
        paymentIntentEntity.setAmount(paymentIntent.getAmount() / 100.0); 
        // Convert cents to dollars 
        paymentIntentEntity.setEmail(paymentIntent.getReceiptEmail()); 
        paymentIntentEntity.setCurrency(paymentIntent.getCurrency()); 
        paymentIntentEntity.setStatus(paymentIntent.getStatus()); 
        paymentRepository.save(paymentIntentEntity); 
        
        return paymentIntent; 
    } 

    public ResponseEntity<String> stripePayment(String userEmail) throws Exception {
        PaymentIntentEntity payment = paymentRepository.findByEmail(userEmail);

        if (payment == null) {
            throw new Exception("Payment information is missing");
        }
        payment.setAmount(00.00);
        paymentRepository.save(payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}