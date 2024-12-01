package com.example.Acmeplex.dataGenerator;

import java.util.ArrayList;
import java.util.List;

import com.example.Acmeplex.request.ShowSeatRequest;
import com.example.Acmeplex.request.TheaterRequest;

public class ShowSeatRequestGenerator {
     public static List<ShowSeatRequest> generateRequests() {
        List<ShowSeatRequest> requests = new ArrayList<>();

        requests.add(new ShowSeatRequest(1));
        requests.add(new ShowSeatRequest(2));
        requests.add(new ShowSeatRequest(3));
        requests.add(new ShowSeatRequest(4));
        requests.add(new ShowSeatRequest(5));
        requests.add(new ShowSeatRequest(6));
        requests.add(new ShowSeatRequest(7));
        requests.add(new ShowSeatRequest(8));
        requests.add(new ShowSeatRequest(9));
        requests.add(new ShowSeatRequest(10));
        requests.add(new ShowSeatRequest(11));
        requests.add(new ShowSeatRequest(12));
        requests.add(new ShowSeatRequest(13));
        requests.add(new ShowSeatRequest(14));
        requests.add(new ShowSeatRequest(15));
        requests.add(new ShowSeatRequest(16));
        requests.add(new ShowSeatRequest(17));
        requests.add(new ShowSeatRequest(18));
        requests.add(new ShowSeatRequest(19));
        requests.add(new ShowSeatRequest(20));

        return requests;
    }
}
