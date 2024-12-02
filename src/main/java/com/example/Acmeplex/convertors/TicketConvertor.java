package com.example.Acmeplex.convertors;

import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.response.TicketResponse;

public class TicketConvertor {

public static TicketResponse returnTicket(Show show, Ticket ticket) {
TicketResponse ticketResponseDto = TicketResponse.builder()
.bookedSeats(ticket.getBookedSeats())
.address(show.getTheater().getAddress())
.theaterName(show.getTheater().getName())
.movieName(show.getMovie().getTitle())
.date(show.getDate())
.time(show.getTime())
.totalPrice(ticket.getTotalTicketsPrice())
.ticketId(ticket.getId())
.build();

return ticketResponseDto;
}
}
