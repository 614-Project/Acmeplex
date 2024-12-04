
package com.example.Acmeplex.services;

import com.example.Acmeplex.convertors.TicketConvertor;
import com.example.Acmeplex.entities.Credit;
import com.example.Acmeplex.entities.Payment;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.TheaterSeat;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.exceptions.SeatsNotAvailable;
import com.example.Acmeplex.exceptions.ShowDoesNotExist;
import com.example.Acmeplex.repositories.CreditRepository;
import com.example.Acmeplex.repositories.ShowRepository;
import com.example.Acmeplex.repositories.ShowSeatRepository;
import com.example.Acmeplex.repositories.TheaterSeatRepository;
import com.example.Acmeplex.repositories.TicketRepository;
import com.example.Acmeplex.repositories.UserRepository;
import com.example.Acmeplex.request.TicketRequest;
import com.example.Acmeplex.response.TicketResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketService {

	@Autowired
	private ShowSeatRepository showSeatRepository;

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

	@Autowired
	private TheaterSeatRepository theaterSeatRepository;

	public TicketResponse ticketBooking(TicketRequest ticketRequest) {
		Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

		if (showOpt.isEmpty()) {
			throw new ShowDoesNotExist();
		}

		Show show = showOpt.get();

		// retrieves list of theatre and showseats
		List<TheaterSeat> theaterSeatList = new ArrayList<>();
		for (String seat : ticketRequest.getRequestSeats()) {
			TheaterSeat theaterSeat = theaterSeatRepository.findBySeatNo(seat);
			theaterSeatList.add(theaterSeat);
		}

		List<ShowSeat> showSeatList = showSeatRepository.findByShowShowId(show.getShowId());
		// for each seat in the theatre create a corresponding showseat and updates showseat
		for (TheaterSeat theaterSeat : theaterSeatList) {
			ShowSeat showSeat = new ShowSeat();
			showSeat.setSeatNo(theaterSeat.getSeatNo());
			showSeat.setTheatreSeat(theaterSeat);

			showSeat.setShow(show);
			showSeat.setIsAvailable(Boolean.FALSE);

			showSeatList.add(showSeat);
		}

		Boolean isSeatAvailable = isSeatAvailable(showSeatList, ticketRequest.getRequestSeats());

		if (!isSeatAvailable) {
			throw new SeatsNotAvailable();
		}


		assignSeats(showSeatList,	ticketRequest.getRequestSeats());

		String seats = listToString(ticketRequest.getRequestSeats());

		Ticket ticket = new Ticket();
		ticket.setTotalTicketsPrice(ticketRequest.getTotal());
		ticket.setBookedSeats(seats);
		ticket.setShow(show);
		ticket.setBookedSeats(seats);
		ticket = ticketRepository.save(ticket);

		show.getTicketList().add(ticket);
		showRepository.save(show);

		return TicketConvertor.returnTicket(show, ticket);
	}

	//method to check if the requested seat is available
	private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
		for (ShowSeat showSeat : showSeatList) {
			String seatNo = showSeat.getSeatNo();

			if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
				return false;
			}
		}

		return true;
	}

  // method to update the seat status
	private void assignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
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


	// Method for cancellation of ticket
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

	public Ticket getTicketById(Long ticketId) {
		return ticketRepository.findById(ticketId)
						.orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));
	}	
}