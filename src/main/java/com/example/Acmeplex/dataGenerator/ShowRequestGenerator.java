package com.example.Acmeplex.dataGenerator;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
// import java.util.Date;
import java.util.List;

import com.example.Acmeplex.request.ShowRequest;

public class ShowRequestGenerator {

    public static List<ShowRequest> generateRequests() {
        List<ShowRequest> requests = new ArrayList<>();

        requests.add(new ShowRequest(LocalTime.parse("14:00:00"), Date.valueOf("2024-12-01"), 1, 1));
        requests.add(new ShowRequest(LocalTime.parse("17:00:00"), Date.valueOf("2024-12-01"), 2, 2));
        requests.add(new ShowRequest(LocalTime.parse("19:30:00"), Date.valueOf("2024-12-02"), 3, 3));
        requests.add(new ShowRequest(LocalTime.parse("20:00:00"), Date.valueOf("2024-12-02"), 4, 4));
        requests.add(new ShowRequest(LocalTime.parse("21:00:00"), Date.valueOf("2024-12-03"), 5, 5));
        requests.add(new ShowRequest(LocalTime.parse("16:00:00"), Date.valueOf("2024-12-03"), 6, 6));
        requests.add(new ShowRequest(LocalTime.parse("13:00:00"), Date.valueOf("2024-12-04"), 7, 7));
        requests.add(new ShowRequest(LocalTime.parse("18:00:00"), Date.valueOf("2024-12-04"), 8, 8));
        requests.add(new ShowRequest(LocalTime.parse("15:00:00"), Date.valueOf("2024-12-05"), 9, 9));
        requests.add(new ShowRequest(LocalTime.parse("19:00:00"), Date.valueOf("2024-12-05"), 10, 10));
        requests.add(new ShowRequest(LocalTime.parse("12:00:00"), Date.valueOf("2024-12-06"), 11, 11));
        requests.add(new ShowRequest(LocalTime.parse("17:30:00"), Date.valueOf("2024-12-06"), 12, 12));
        requests.add(new ShowRequest(LocalTime.parse("20:30:00"), Date.valueOf("2024-12-07"), 13, 13));
        requests.add(new ShowRequest(LocalTime.parse("14:30:00"), Date.valueOf("2024-12-07"), 14, 14));
        requests.add(new ShowRequest(LocalTime.parse("16:30:00"), Date.valueOf("2024-12-08"), 16, 16));
        requests.add(new ShowRequest(LocalTime.parse("13:30:00"), Date.valueOf("2024-12-09"), 17, 17));
        requests.add(new ShowRequest(LocalTime.parse("18:30:00"), Date.valueOf("2024-12-09"), 18, 10));
        requests.add(new ShowRequest(LocalTime.parse("15:30:00"), Date.valueOf("2024-12-10"), 19, 11));
        requests.add(new ShowRequest(LocalTime.parse("19:30:00"), Date.valueOf("2024-12-10"), 20, 12));

        return requests;
    }
}
