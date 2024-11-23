package com.example.Acmeplex.request;

import lombok.Data;

@Data
public class PaymentRequest {
    //private String Name;
    private int amount;
    private String currency;
    private String receiptEmail;
}