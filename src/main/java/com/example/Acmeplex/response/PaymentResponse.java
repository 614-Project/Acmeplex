package com.example.Acmeplex.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class PaymentResponse {

    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;
    private String amount;
    private String currency;

}
