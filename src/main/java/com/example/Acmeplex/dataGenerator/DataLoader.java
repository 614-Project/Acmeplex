// package com.example.Acmeplex.dataGenerator;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
//
// import java.util.List;
// import java.util.function.Consumer;
// import java.util.stream.Collectors;
//
// import com.example.Acmeplex.entities.Movie;
// import com.example.Acmeplex.entities.Show;
// import com.example.Acmeplex.entities.Theater;
// import com.example.Acmeplex.entities.TheaterSeat;
// import com.example.Acmeplex.exceptions.TheaterAlreadyExists;
// import com.example.Acmeplex.request.TheaterRequest;
// import com.example.Acmeplex.request.ShowRequest;
// import com.example.Acmeplex.request.ShowSeatRequest;
// import com.example.Acmeplex.request.TheaterSeatRequest;
// import com.example.Acmeplex.services.MovieService;
// import com.example.Acmeplex.services.ShowService;
// //import com.example.Acmeplex.services.TheaterSeatService;
// import com.example.Acmeplex.services.TheaterService;
//
// @Component
// public class DataLoader implements CommandLineRunner {
//
//     @Autowired
//     private MovieService movieService;
//
//     @Autowired
//     private TheaterService theaterService;
//
//     @Autowired
//     private ShowService showService;
//
//     // @Autowired
//     // private TheaterSeatService theaterSeatService;
//
//     @Override
//     public void run(String... args) throws Exception {
//         // Add Movie data
//         List<Movie> movies = MovieRequestGenerator.generateRequests().stream()
//             .map(request -> new Movie(
//                 request.getTitle(),
//                 request.getDescription(),
//                 request.getDuration(),
//                 request.getGenre(),
//                 request.getReleaseDate(),
//                 request.getRating(),
//                 request.getDirector(),
//                 request.getCast(),
//                 request.getBannerUrl(),
//                 request.getCarouselUrl(),
//                 request.getTrailerUrl()))
//             .collect(Collectors.toList());
//         movies.forEach(movieService::addMovie);
//
//         //Add Theater data
//        List<TheaterRequest> theaterRequests = TheaterRequestGenerator.generateRequests();
//
//         theaterRequests.forEach(request -> {
//             try {
//                 theaterService.addTheater(request);
//             } catch (TheaterAlreadyExists e) {
//                 System.err.println("Theater already exists: " + e.getMessage());
//             } catch (Exception e) {
//                 System.err.println("Unexpected error: " + e.getMessage());
//             }
//         });
//
//         // // Add TheaterSeat data
//         List<TheaterSeatRequest> theaterSeatRequest = TheaterSeatRequestGenerator.generateRequests();
//         theaterSeatRequest.forEach(request -> {
//             try {
//                 theaterService.addTheaterSeat(request);
//             } catch (TheaterAlreadyExists e) {
//                 System.err.println("Theater already exists: " + e.getMessage());
//             } catch (Exception e) {
//                 System.err.println("Unexpected error: " + e.getMessage());
//             }
//         });
//
//         // Add Show data
//         List<ShowRequest> showsRequest = ShowRequestGenerator.generateRequests();
//         showsRequest.forEach(request-> {
//                 try {
//                 showService.addShow(request);
//                 } catch (TheaterAlreadyExists e) {
//                     System.err.println("Theater already exists: " + e.getMessage());
//                 } catch (Exception e) {
//                     System.err.println("Unexpected error: " + e.getMessage());
//                 }
//         });
//
//
//         // Add Show data
//         List<ShowSeatRequest> showSeatRequest = ShowSeatRequestGenerator.generateRequests();
//         showSeatRequest.forEach(request-> {
//                 try {
//                 showService.associateShowSeats(request);
//                 } catch (TheaterAlreadyExists e) {
//                     System.err.println("Theater already exists: " + e.getMessage());
//                 } catch (Exception e) {
//                     System.err.println("Unexpected error: " + e.getMessage());
//                 }
//         });
//
//     }
// }
