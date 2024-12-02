// package com.example.Acmeplex;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.example.Acmeplex.dataGenerator.MovieRequestGenerator;
// import com.example.Acmeplex.dataGenerator.ShowRequestGenerator;
// import com.example.Acmeplex.dataGenerator.TheaterRequestGenerator;
// import com.example.Acmeplex.dataGenerator.TheaterSeatRequestGenerator;
// import com.example.Acmeplex.entities.Movie;
// import com.example.Acmeplex.entities.Show;
// import com.example.Acmeplex.entities.Theater;
// import com.example.Acmeplex.entities.TheaterSeat;
// import com.example.Acmeplex.request.MovieRequest;
// import com.example.Acmeplex.services.MovieService;
// import com.example.Acmeplex.services.ShowService;
// import com.example.Acmeplex.services.TheaterService;

// @Component
// public class DataLoader implements CommandLineRunner {

// @Autowired
// private MovieService movieService;

// @Autowired
// private TheaterService theaterService;

// @Autowired
// private ShowService showService;

// @Override
// public void run(String... args) throws Exception {
// // Add Movie data
// List<MovieRequest> movieRequests = MovieRequestGenerator.generateRequests();
// List<Movie> movies =
// movieRequests.stream().map(convertors::MovieConvertor).collect(Collectors.toList());
// movies.forEach(movieService::saveMovie); }

// // List<Movie> movies = MovieRequestGenerator.generateRequests().stream()
// // .map(request -> new Movie(request.getTitle(),
// // request.getDescription(),
// // request.getDuration(),
// // request.getGenre(),
// // request.getReleaseDate(),
// // request.getRating(),
// // request.getDirector(),
// // request.getCast(),
// // request.getBannerUrl(),
// // request.getCarouselUrl(),
// // request.getTrailerUrl()))
// // .collect(Collectors.toList());
// // movies.forEach(movie -> movieService.addMovie(movie));

// // Add Theater data
// List<Theater> theaters = TheaterRequestGenerator.generateRequests().stream()
// .map(request -> new Theater(request.getName(), request.getAddress()))
// .collect(Collectors.toList());
// theaters.forEach(theater -> theaterService.addTheater(theater));

// // Add TheaterSeat data
// List<TheaterSeat> theaterSeats =
// TheaterSeatRequestGenerator.generateRequests().stream()
// .map(request -> new TheaterSeat(request.getAddress(),
// request.getNumberOfSeatPerRow(),
// request.getNumberOfRows()))
// .collect(Collectors.toList());
// theaterSeats.forEach(seat -> theaterSeatService.createTheaterSeat(seat));

// // Add Show data
// List<Show> shows = ShowRequestGenerator.generateRequests().stream()
// .map(request -> new Show(request.getShowStartTime(), request.getShowDate(),
// request.getTheaterId(),
// request.getMovieId()))
// .collect(Collectors.toList());
// shows.forEach(show -> showService.createShow(show));
// }
// }
