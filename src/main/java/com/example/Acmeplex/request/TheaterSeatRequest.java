package com.example.Acmeplex.request;

import lombok.Data;

@Data
public class TheaterSeatRequest {
    private String address;
    private Integer numberOfSeatPerRow;
    private Integer numberOfRows;
}