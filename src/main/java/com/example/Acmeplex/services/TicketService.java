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
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.convertors.TicketConvertor;
import com.example.Acmeplex.entities.Credit;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.exceptions.SeatsNotAvailable;
import com.example.Acmeplex.exceptions.ShowDoesNotExist;
import com.example.Acmeplex.repositories.CreditRepository;
import com.example.Acmeplex.repositories.ShowRepository;
import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.repositories.UserRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.TicketResponse;
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

    @Autowired
	private ShowRepository showRepository;

    @Autowired
    private EmailService emailService;

	public TicketResponse ticketBooking(TicketRequest ticketRequest) {
		Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

		if (showOpt.isEmpty()) {
			throw new ShowDoesNotExist();
		}

		// Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

		// if (userOpt.isEmpty()) {
		// 	throw new UserDoesNotExists();
		// }

		// User user = userOpt.get();
		Show show = showOpt.get();

		Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(), ticketRequest.getRequestSeats());

		if (!isSeatAvailable) {
			throw new SeatsNotAvailable();
		}

		// count price
		assignSeats(show.getShowSeatList(),	ticketRequest.getRequestSeats());

		String seats = listToString(ticketRequest.getRequestSeats());

		Ticket ticket = new Ticket();
		ticket.setTotalTicketsPrice(ticketRequest.getTotal());
		ticket.setBookedSeats(seats);
		//ticket.setUser(user);
		ticket.setShow(show);

		ticket = ticketRepository.save(ticket);

		//user.getTicketList().add(ticket);
		show.getTicketList().add(ticket);
		//userRepository.save(user);
		showRepository.save(show);

		return TicketConvertor.returnTicket(show, ticket);
	}

	private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
		for (ShowSeat showSeat : showSeatList) {
			String seatNo = showSeat.getSeatNo();

			if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
				return false;
			}
		}

		return true;
	}

	// private Long getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
	// 	Long totalAmount = 0L;

	// 	for (ShowSeat showSeat : showSeatList) {
	// 		if (requestSeats.contains(showSeat.getSeatNo())) {
	// 			totalAmount += showSeat.getPrice().longValue();
	// 			showSeat.setIsAvailable(Boolean.FALSE);
	// 		}
	// 	}

	// 	return totalAmount;
	// }
    private void assignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
        // Iterate through the list of ShowSeat objects and update availability for requested seats
        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                showSeat.setIsAvailable(Boolean.FALSE); // Mark seat as unavailable
            }
        }
    }
    

	private String listToString(List<String> requestSeats) {
		StringBuilder sb = new StringBuilder();

		for (String s : requestSeats) {
			sb.append(s).append(",");
		}

		return sb.toString();
	}


    // Ticket cancellation
    public void cancelTicket(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));


        if ("PENDING".equals(ticket.getStatus()) || ticket.getExpireDate().isAfter(LocalDateTime.now())) {

            Payment payment = ticket.getPayment();
            if (payment == null) {
                throw new RuntimeException("Payment information not found, ticket might be expired for the ticket.");
            }

            User user = userRepository.findByEmail(payment.getCustomerEmail()).orElse(null);

            // Calculate the credit value
            int creditValue = (user != null && user.getEmail().equalsIgnoreCase(payment.getCustomerEmail())) 
                    ? 1000 
                    : 850; 

            // Update user's credit if the user exists
            if (user != null) {
                if(user.getCredit()==null){
                    user.setCredit(0);
                }
                user.setCredit(user.getCredit() + creditValue);
                userRepository.save(user); 
            }

            // Create and save the credit
            Credit credit = new Credit();
            credit.setCredit(creditValue);
            credit.setExpireDate(LocalDateTime.now().plusYears(1));
            credit.setTicket(ticket);
            creditRepository.save(credit);


            ticket.setStatus("CANCELED");
            ticketRepository.save(ticket);

            //Send email to customer about ticket cancelation
            String customerEmail = payment.getCustomerEmail();
            String customerName = payment.getCustomerName();
            emailService.sendTicketCancelEmail(customerEmail, credit, customerName);

            System.out.println("Ticket canceled successfully.");
        } else {

            throw new RuntimeException("Ticket cannot be canceled as it is not pending or has expired.");
        }
    }
}