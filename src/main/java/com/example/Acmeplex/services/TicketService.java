// package com.example.Acmeplex.services;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// // import com.example.Acmeplex.convertor.TicketConvertor;
// import com.example.Acmeplex.entities.Show;
// // import com.example.Acmeplex.entities.ShowSeat;
// import com.example.Acmeplex.entities.Ticket;
// import com.example.Acmeplex.entities.User;
// // import com.example.Acmeplex.exceptions.SeatsNotAvailable;
// // import com.example.Acmeplex.exceptions.ShowDoesNotExists;
// // import com.example.Acmeplex.exceptions.UserDoesNotExists;
// // import com.example.Acmeplex.repositories.ShowRepository;
// import com.example.Acmeplex.repositories.TicketRepository;
// import com.example.Acmeplex.repositories.UserRepository;
// import com.example.Acmeplex.request.TicketRequest;
// import com.example.Acmeplex.response.TicketResponse;

// @Service
// public class TicketService {

// @Autowired
// private TicketRepository ticketRepository;

// @Autowired
// private ShowRepository showRepository;

// @Autowired
// private UserRepository userRepository;

// public TicketResponse ticketBooking(TicketRequest ticketRequest) {
// Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

// if (showOpt.isEmpty()) {
// throw new ShowDoesNotExist();
// }

// Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

// if (userOpt.isEmpty()) {
// throw new UserDoesNotExists();
// }

// User user = userOpt.get();
// Show show = showOpt.get();

// Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(),
// ticketRequest.getRequestSeats());

// if (!isSeatAvailable) {
// throw new SeatsNotAvailable();
// }

// // count price
// Integer getPriceAndAssignSeats =
// getPriceAndAssignSeats(show.getShowSeatList(),
// ticketRequest.getRequestSeats());

// String seats = listToString(ticketRequest.getRequestSeats());

// Ticket ticket = new Ticket();
// ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
// ticket.setBookedSeats(seats);
// ticket.setUser(user);
// ticket.setShow(show);

// ticket = ticketRepository.save(ticket);

// user.getTicketList().add(ticket);
// show.getTicketList().add(ticket);
// userRepository.save(user);
// showRepository.save(show);

// return TicketConvertor.returnTicket(show, ticket);
// }

// private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String>
// requestSeats) {
// for (ShowSeat showSeat : showSeatList) {
// String seatNo = showSeat.getSeatNo();

// if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
// return false;
// }
// }

// return true;
// }

// private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList,
// List<String> requestSeats) {
// Integer totalAmount = 0;

// for (ShowSeat showSeat : showSeatList) {
// if (requestSeats.contains(showSeat.getSeatNo())) {
// totalAmount += showSeat.getPrice();
// showSeat.setIsAvailable(Boolean.FALSE);
// }
// }

// return totalAmount;
// }

// private String listToString(List<String> requestSeats) {
// StringBuilder sb = new StringBuilder();

// for (String s : requestSeats) {
// sb.append(s).append(",");
// }

// return sb.toString();
// }

// }
package com.example.Acmeplex.services;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.entities.Credit;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.repositories.CreditRepository;
import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.repositories.UserRepository;
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