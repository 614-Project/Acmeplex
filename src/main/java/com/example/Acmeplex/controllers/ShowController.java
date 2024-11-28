package com.example.Acmeplex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.request.ShowRequest;
import com.example.Acmeplex.request.ShowSeatRequest;
import com.example.Acmeplex.services.ShowService;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("/movie/{movieId}/theater/{theaterId}")
    public List<Show> getShowsByMovieAndTheater(@PathVariable Integer movieId, @PathVariable Integer theaterId) {
        return showService.getShowsByMovieAndTheater(movieId, theaterId);
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> addShow(@RequestBody ShowRequest showRequest) {
        try {
            String result = showService.addShow(showRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatRequest showSeatRequest) {
        try {
            String result = showService.associateShowSeats(showSeatRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
