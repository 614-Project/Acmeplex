package com.example.Acmeplex.convertors;

import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.request.ShowRequest;

import java.time.LocalTime;

public class ShowConvertor {

    /*
     * This method helps to convert a show request to a show object(entity)
     * before saving the data to the database.
     */
    public static Show showDtoToShow(ShowRequest showRequest) {
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();

        return show;
    }
}
