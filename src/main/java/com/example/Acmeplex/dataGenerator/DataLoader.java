// package com.example.Acmeplex.dataGenerator;

// // package com.example.Acmeplex;

// // import java.util.List;
// // import java.util.stream.Collectors;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.CommandLineRunner;
// // import org.springframework.stereotype.Component;

// // import com.example.Acmeplex.dataGenerator.MovieRequestGenerator;
// // import com.example.Acmeplex.dataGenerator.ShowRequestGenerator;
// // import com.example.Acmeplex.dataGenerator.TheaterRequestGenerator;
// // import com.example.Acmeplex.dataGenerator.TheaterSeatRequestGenerator;
// // import com.example.Acmeplex.entities.Movie;
// // import com.example.Acmeplex.entities.Show;
// // import com.example.Acmeplex.entities.Theater;
// // import com.example.Acmeplex.entities.TheaterSeat;
// // import com.example.Acmeplex.request.MovieRequest;
// // import com.example.Acmeplex.services.MovieService;
// // import com.example.Acmeplex.services.ShowService;
// // import com.example.Acmeplex.services.TheaterService;

// // @Component
// // public class DataLoader implements CommandLineRunner {

// // @Autowired
// // private MovieService movieService;

// // @Autowired
// // private TheaterService theaterService;

// // @Autowired
// // private ShowService showService;

// // @Override
// // public void run(String... args) throws Exception {
// // // Add Movie data
// // List<MovieRequest> movieRequests = MovieRequestGenerator.generateRequests();
// // List<Movie> movies =
// // movieRequests.stream().map(convertors::MovieConvertor).collect(Collectors.toList());
// // movies.forEach(movieService::saveMovie); }

// // List<Movie> movies = MovieRequestGenerator.generateRequests().stream()
// // .map(request -> new Movie(request.getTitle(),
// // request.getDescription(),
// // request.getDuration(),
// // request.getGenre(),
// // request.getMovieType(),
// // request.getReleaseDate(),
// // request.getRating(),
// // request.getDirector(),
// // request.getCast(),
// // request.getUrl(),
// // request.getTrailerUrl()))
// // .collect(Collectors.toList());
// // movies.forEach(movie -> movieService.addMovie(movie));

// // // Add Theater data
// // List<Theater> theaters = TheaterRequestGenerator.generateRequests().stream()
// // .map(request -> new Theater(request.getName(), request.getAddress()))
// // .collect(Collectors.toList());
// // theaters.forEach(theater -> theaterService.addTheater(theater));

// // // Add TheaterSeat data
// // List<TheaterSeat> theaterSeats =
// // TheaterSeatRequestGenerator.generateRequests().stream()
// // .map(request -> new TheaterSeat(request.getAddress(),
// // request.getNumberOfSeatPerRow(),
// // request.getNumberOfRows()))
// // .collect(Collectors.toList());
// // theaterSeats.forEach(seat -> theaterSeatService.createTheaterSeat(seat));

// // // Add Show data
// // List<Show> shows = ShowRequestGenerator.generateRequests().stream()
// // .map(request -> new Show(request.getShowStartTime(), request.getShowDate(),
// // request.getTheaterId(),
// // request.getMovieId()))
// // .collect(Collectors.toList());
// // shows.forEach(show -> showService.createShow(show));
// // }
// // }


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import java.util.List;
// import java.util.function.Consumer;
// import java.util.stream.Collectors;

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

// @Component
// public class DataLoader implements CommandLineRunner {

//     @Autowired
//     private MovieService movieService;

//     @Autowired
//     private TheaterService theaterService;

//     @Autowired
//     private ShowService showService;

//     // @Autowired
//     // private TheaterSeatService theaterSeatService;

//     @Override
//     public void run(String... args) throws Exception {
//         // Add Movie data
//         List<Movie> movies = MovieRequestGenerator.generateRequests().stream()
//             .map(request -> new Movie(
//                 request.getTitle(),
//                 request.getDescription(),
//                 request.getDuration(),
//                 request.getGenre(),
//                 request.getMovieType(),
//                 request.getReleaseDate(),
//                 request.getRating(),
//                 request.getDirector(),
//                 request.getCast(),
//                 request.getUrl(),
//                 request.getTrailerUrl()))
//             .collect(Collectors.toList());
//         movies.forEach(movieService::addMovie);

//         //Add Theater data
//        List<TheaterRequest> theaterRequests = TheaterRequestGenerator.generateRequests();

//         theaterRequests.forEach(request -> {
//             try {
//                 theaterService.addTheater(request);
//             } catch (TheaterAlreadyExists e) {
//                 System.err.println("Theater already exists: " + e.getMessage());
//             } catch (Exception e) {
//                 System.err.println("Unexpected error: " + e.getMessage());
//             }
//         });

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

//     }    
// }
