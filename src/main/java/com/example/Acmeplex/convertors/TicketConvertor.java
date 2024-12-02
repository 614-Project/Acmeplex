package com.example.Acmeplex.convertors;

import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.Ticket;
import com.example.Acmeplex.response.TicketResponse;

public class TicketConvertor {
      /*
     * This method helps to convert a ticket request to a ticket object(entity)
     * before saving the data to the database.
     */
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
