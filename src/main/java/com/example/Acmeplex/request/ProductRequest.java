package com.example.Acmeplex.request;

import lombok.Data;

@Data
public class ProductRequest {
        private String name;
        private Long amount;
        private String currency;
        private Long quantity;
        private String sessionID;
}
