package com.mtb.controller;

import com.mtb.entity.MovieDetails;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.Movie;
import com.mtb.service.MovieService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {
    Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MovieService movieService;

    @PostMapping
    ResponseEntity<Response<MovieDetails>> createMovie(@RequestBody @Valid Movie request) {
        logger.info("Request received to add movie {}", request.toString());
        //Translate Request
        MovieDetails newMovie = new MovieDetails();
        newMovie.setTitle(request.getTitle());
        newMovie.setStarring(request.getStarring());
        newMovie.setDescription(request.getDescription());
        newMovie.setDuration(request.getDuration());
      //  newMovie.setGenreId(request.getGenre());
        newMovie.setRating(request.getRating());
        newMovie.setReleaseDate(request.getReleaseDate());
        //Calling the service
        MovieDetails movieDetails = movieService.createMovie(newMovie);
        //Handling response
        logger.info("Request completed to add movie with {}", movieDetails.getMovieId());
        return ResponseEntity.ok(new Response<MovieDetails>(null, ApplicationConstants.MOVIE_CREATED, true, movieDetails));
    }
    /*

    @GetMapping("/{theatreId}")
    ResponseEntity<Response<TheatreDetails>> getTheatre(@PathVariable @NotNull String theatreId) throws InvalidDataException {
        logger.info("Request received to get theatre with id {}", theatreId);
        //Calling the service
        TheatreDetails theatreDetails = theatreService.getTheatre(theatreId);
        //Handling response
        logger.info("Request completed to get theatre with id {}", theatreId);
        return ResponseEntity.ok(new Response<TheatreDetails>(null, ApplicationConstants.THEATRE_RETRIEVED, true, theatreDetails));
    }*/

    @GetMapping
    ResponseEntity<Response<List<MovieDetails>>> getAllMovies() {
        logger.info("Request received to get all movies");
        //Calling the service
        List<MovieDetails> movieDetails = movieService.getAllMovies();
        //Handling response
        logger.info("Request completed to get all movies");
        return ResponseEntity.ok(new Response<List<MovieDetails>>(null, ApplicationConstants.MOVIE_RETRIEVED, true, movieDetails));
    }

/*
    @PutMapping
    ResponseEntity<Response<TheatreDetails>> updateTheatre(@RequestBody @Valid Theatre theatre) throws InvalidDataException {
        logger.info("Request received to update theatre {}", theatre.getCode());
        //Calling the service
        TheatreDetails updatedTheatre = theatreService.updateTheatre(theatre);
        //Handling response
        logger.info("Request completed to update theatre {}", theatre.getCode());
        return ResponseEntity.ok(new Response<TheatreDetails>(null, ApplicationConstants.THEATRE_UPDATED, true, updatedTheatre));
    }

    @DeleteMapping("/{theatreId}")
    ResponseEntity<Response<String>> deleteTheatre(@PathVariable @NotNull String theatreId) throws InvalidDataException {
        logger.info("Request received to delete theatre with id {}", theatreId);
        //Calling the service
        theatreService.deleteTheatre(theatreId);
        //Handling response
        logger.info("Request completed to delete theatre with id {}", theatreId);
        return ResponseEntity.ok(new Response<String>(null, ApplicationConstants.THEATRE_DELETED, true, null));
    }*/

}
