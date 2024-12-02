package com.example.Acmeplex.response;

import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Long ticketId;
    private LocalTime time;
    private Date date;
    private String movieName;
    private String theaterName;
    private String address;
    private String bookedSeats;
    private Long totalPrice;
}
