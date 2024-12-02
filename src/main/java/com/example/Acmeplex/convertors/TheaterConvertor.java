package com.example.Acmeplex.convertors;

import com.example.Acmeplex.entities.Theater;
import com.example.Acmeplex.request.TheaterRequest;

public class TheaterConvertor {

          /*
     * This method helps to convert a theater request to a theater object(entity)
     * before saving the data to the database.
     */
    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
        Theater theater = Theater.builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();
        return theater;
    }
}