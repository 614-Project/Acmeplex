package com.example.Acmeplex.dataGenerator;

import java.util.ArrayList;
import java.util.List;

import com.example.Acmeplex.request.TheaterSeatRequest;

public class TheaterSeatRequestGenerator {

    public static List<TheaterSeatRequest> generateRequests() {
        List<TheaterSeatRequest> requests = new ArrayList<>();

        requests.add(new TheaterSeatRequest("123 Main St, Calgary, AB", 20, 15));
        requests.add(new TheaterSeatRequest("456 Center Ave, Calgary, AB", 18, 12));
        requests.add(new TheaterSeatRequest("789 Hillside Dr, Banff, AB", 22, 10));
        requests.add(new TheaterSeatRequest("321 River Rd, Edmonton, AB", 25, 20));
        requests.add(new TheaterSeatRequest("654 Sunshine Blvd, Red Deer, AB", 16, 8));
        requests.add(new TheaterSeatRequest("987 Northern Lights St, Fort McMurray, AB", 24, 18));
        requests.add(new TheaterSeatRequest("111 Urban Dr, Lethbridge, AB", 30, 25));
        requests.add(new TheaterSeatRequest("222 Ocean View Rd, Vancouver, BC", 28, 22));
        requests.add(new TheaterSeatRequest("333 Pine St, Victoria, BC", 20, 15));
        requests.add(new TheaterSeatRequest("444 Golden Gate Ave, San Francisco, CA", 26, 18));
        requests.add(new TheaterSeatRequest("555 Freedom St, New York, NY", 18, 12));
        requests.add(new TheaterSeatRequest("666 Sunset Blvd, Los Angeles, CA", 24, 16));
        requests.add(new TheaterSeatRequest("777 Tower St, Toronto, ON", 22, 14));
        requests.add(new TheaterSeatRequest("888 Maple Ave, Ottawa, ON", 20, 10));
        requests.add(new TheaterSeatRequest("999 King's Rd, London, UK", 28, 18));
        requests.add(new TheaterSeatRequest("101 Main Square, Calgary, AB", 30, 20));
        requests.add(new TheaterSeatRequest("202 Historic Lane, Quebec City, QC", 18, 12));
        requests.add(new TheaterSeatRequest("303 Coastal Hwy, San Diego, CA", 24, 14));
        requests.add(new TheaterSeatRequest("404 Sunset Dr, Miami, FL", 20, 10));
        requests.add(new TheaterSeatRequest("505 Moonlight St, Las Vegas, NV", 26, 15));

        return requests;
    }
}
