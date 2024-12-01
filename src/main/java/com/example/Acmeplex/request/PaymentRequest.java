package com.example.Acmeplex.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PaymentRequest {
    //private String Name;

        private Long total;
        // private String sessionID;
        private String currency;
        private long quantity;
        // private Integer showId;

        private Long ticketId;
        private Long amount;
        private List<String> requestSeats;
}