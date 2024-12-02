package com.example.Acmeplex.request;

import java.util.List;
import lombok.Data;

@Data
public class PaymentRequest {

        private Long total;
        private String currency;
        private long quantity;
        private Long ticketId;
        private Long amount;
        private List<String> requestSeats;
}