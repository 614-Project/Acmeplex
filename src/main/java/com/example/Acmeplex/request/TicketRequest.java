package com.example.Acmeplex.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TicketRequest {
        private String movieName;
        private Long amount;
        private String location;
        private String seat;        
        private LocalDateTime showTime;
        private Long total;
        private String sessionID;
        private String currency;
        private long quantity;
}
