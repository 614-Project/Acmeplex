package com.example.Acmeplex.convertors;

import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.request.ShowRequest;

public class ShowConvertor {

    public static Show showDtoToShow(ShowRequest showRequest) {
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();

        return show;
    }
}

public class ShowConvertor {

}
