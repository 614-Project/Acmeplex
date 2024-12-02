package com.example.Acmeplex.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TicketRequest {

        private Long total;       
        private Integer showId;
        private Integer userId;
        private List<String> requestSeats;
}
