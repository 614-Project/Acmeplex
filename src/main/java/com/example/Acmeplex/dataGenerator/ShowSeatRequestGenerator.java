package com.example.Acmeplex.dataGenerator;

import java.util.ArrayList;
import java.util.List;

import com.example.Acmeplex.request.ShowSeatRequest;
import com.example.Acmeplex.request.TheaterRequest;

public class ShowSeatRequestGenerator {
     public static List<ShowSeatRequest> generateRequests() {
        List<ShowSeatRequest> requests = new ArrayList<>();

        requests.add(new ShowSeatRequest(1));
        requests.add(new ShowSeatRequest());
        requests.add(new ShowSeatRequest(3));
        requests.add(new ShowSeatRequest(4));
        requests.add(new ShowSeatRequest(5));
        requests.add(new ShowSeatRequest(6));
        requests.add(new ShowSeatRequest(7));
        requests.add(new ShowSeatRequest(8));
        requests.add(new ShowSeatRequest(9));
        requests.add(new ShowSeatRequest(10));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));
        // requests.add(new ShowSeatRequest(1));

        return requests;
    }
}
