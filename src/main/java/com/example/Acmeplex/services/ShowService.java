package com.example.Acmeplex.services;

import java.util.List;
import java.util.Optional;

import com.example.Acmeplex.repositories.ShowSeatRepository;
import com.example.Acmeplex.response.TheatreSeatStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Acmeplex.convertors.ShowConvertor;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.ShowSeat;
import com.example.Acmeplex.entities.Theater;
import com.example.Acmeplex.entities.TheaterSeat;
import com.example.Acmeplex.exceptions.MovieNotFoundException;
import com.example.Acmeplex.exceptions.ShowDoesNotExist;
import com.example.Acmeplex.exceptions.TheaterDoesNotExist;
import com.example.Acmeplex.repositories.MovieRepository;
import com.example.Acmeplex.repositories.ShowRepository;
import com.example.Acmeplex.repositories.TheaterRepository;
import com.example.Acmeplex.request.ShowRequest;
import com.example.Acmeplex.request.ShowSeatRequest;

import jakarta.transaction.Transactional;

@Service
public class ShowService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    // Method to get list of shows available for a particular movie and theatre.
    @Transactional
    public List<Show> getShowsByMovieAndTheater(Integer movieId, Integer theaterId) {
        return showRepository.findShowsByMovieAndTheater(movieId, theaterId);
    }

    // This method is used to add a show to the database
    @Transactional
    public String addShow(ShowRequest showRequest) {

        Show show = ShowConvertor.showDtoToShow(showRequest);

        // Tries to fetch a movie, throws exception if movie does not exist
        Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());
        if (movieOpt.isEmpty()) {
            throw new MovieNotFoundException("Movie not found with ID: " + showRequest.getMovieId());
        }

        // Tries to fetch a theater throws exception if theatre does not exist
        Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getTheaterId());
        if (theaterOpt.isEmpty()) {
            throw new TheaterDoesNotExist();
        }

        // sets the movie and theater properties for the show
        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();
        show.setMovie(movie);
        show.setTheater(theater);

        // saves the show entity to the database
        show = showRepository.save(show);

        // adds the show to the list of shows for that movie and theatre
        movie.getShows().add(show);
        theater.getShowList().add(show);

        // saves the updated movies and theatres
        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added Successfully";
    }

    public List<TheatreSeatStatusDTO> seatDistribution(Long theatreId, Long showtimeId) {
        List<TheatreSeatStatusDTO> theaterSeats = showSeatRepository.fetchSeatDistributionForShowtime(theatreId, showtimeId);
        return theaterSeats;
    }

    // this method associates seats with a show by creating showseat entities for each seat
    @Transactional
    public String associateShowSeats(ShowSeatRequest showSeatRequest) throws ShowDoesNotExist {

        Optional<Show> showOpt = showRepository.findById(showSeatRequest.getShowId());
        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExist();
        }
        // retrieves theatre associated with the show
        Show show = showOpt.get();
        Theater theater = show.getTheater();

        // retrieves list of theatre and showseats
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = showSeatRepository.findByShowShowId(show.getShowId());

        // for each seat in the theatre create a corresponding showseat and updates showseat
        for (TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setTheatreSeat(theaterSeat);

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            // showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }

        // saves the updated showseat
        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

}
