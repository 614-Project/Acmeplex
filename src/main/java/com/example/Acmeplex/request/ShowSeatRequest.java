package com.example.Acmeplex.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatRequest {
    private Integer showId;
    // private Integer priceOfPremiumSeat;
    // private Integer priceOfClassicSeat;
}