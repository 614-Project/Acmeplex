package com.example.Acmeplex.dataGenerator;

import java.util.ArrayList;
import java.util.List;

import com.example.Acmeplex.request.TheaterSeatRequest;

public class TheaterSeatRequestGenerator {

    public static List<TheaterSeatRequest> generateRequests() {
        List<TheaterSeatRequest> requests = new ArrayList<>();

        requests.add(new TheaterSeatRequest("123 Main St, Calgary, AB", 5, 10));
        requests.add(new TheaterSeatRequest("456 Center Ave, Calgary, AB", 5, 10));
        requests.add(new TheaterSeatRequest("789 Hillside Dr, Banff, AB", 5, 10));
        requests.add(new TheaterSeatRequest("321 River Rd, Edmonton, AB", 5, 10));
        requests.add(new TheaterSeatRequest("654 Sunshine Blvd, Red Deer, AB", 5, 10));
        requests.add(new TheaterSeatRequest("987 Northern Lights St, Fort McMurray, AB", 5, 10));
        requests.add(new TheaterSeatRequest("111 Urban Dr, Lethbridge, AB", 5, 10));
        requests.add(new TheaterSeatRequest("222 Ocean View Rd, Vancouver, BC", 5, 10));
        requests.add(new TheaterSeatRequest("333 Pine St, Victoria, BC", 5, 10));
        requests.add(new TheaterSeatRequest("444 Golden Gate Ave, San Francisco, CA", 5, 10));
        requests.add(new TheaterSeatRequest("555 Freedom St, New York, NY", 5, 10));
        requests.add(new TheaterSeatRequest("666 Sunset Blvd, Los Angeles, CA", 5, 10));
        requests.add(new TheaterSeatRequest("777 Tower St, Toronto, ON", 5, 10));
        requests.add(new TheaterSeatRequest("888 Maple Ave, Ottawa, ON", 5, 10));
        requests.add(new TheaterSeatRequest("999 King's Rd, London, UK", 5, 10));
        requests.add(new TheaterSeatRequest("101 Main Square, Calgary, AB", 5, 10));
        requests.add(new TheaterSeatRequest("202 Historic Lane, Quebec City, QC", 5, 10));
        requests.add(new TheaterSeatRequest("303 Coastal Hwy, San Diego, CA", 5, 10));
        requests.add(new TheaterSeatRequest("404 Sunset Dr, Miami, FL", 5, 10));
        requests.add(new TheaterSeatRequest("505 Moonlight St, Las Vegas, NV", 5, 10));        

        return requests;
    }
}
