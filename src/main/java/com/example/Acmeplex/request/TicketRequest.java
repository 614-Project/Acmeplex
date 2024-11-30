package com.example.Acmeplex.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TicketRequest {
        // private String movieName;
        // private Long amount;
        // private String location;
        // private String seat;        
        // private LocalDateTime showTime;

        // private String sessionID;
        // private String currency;
        // private long quantity;
        
        private Long total;       
        private Integer showId;
        private Integer userId;
        private List<String> requestSeats;
}
