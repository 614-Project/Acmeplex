package com.example.Acmeplex.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeatRequest {
    private String address;
    private Integer numberOfSeatPerRow;
    private Integer numberOfRows;
}