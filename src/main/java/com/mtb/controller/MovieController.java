package com.mtb.controller;

import com.mtb.entity.Movies;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.Movie;
import com.mtb.service.MovieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    ResponseEntity<Response<Movies>> createMovie(@RequestBody @Valid Movie request) throws InvalidDataException {
        logger.info("Request received to add movie {}", request.toString());
        //Calling the service
        Movies movies = movieService.createMovie(request);
        //Handling response
        logger.info("Request completed to add movie with {}", movies.getMovieId());
        return ResponseEntity.ok(new Response<Movies>(null, ApplicationConstants.MOVIE_CREATED, true, movies));
    }

    @GetMapping("/{movieId}")
    ResponseEntity<Response<Movies>> getMovie(@PathVariable @NotNull String movieId) throws InvalidDataException, InvalidDataException {
        logger.info("Request received to get movie with id {}", movieId);
        //Calling the service
        Movies movies = movieService.getMovie(movieId);
        //Handling response
        logger.info("Request completed to get movie with id {}", movieId);
        return ResponseEntity.ok(new Response<Movies>(null, ApplicationConstants.MOVIE_RETRIEVED, true, movies));
    }

    @GetMapping
    ResponseEntity<Response<List<Movies>>> getAllMovies() {
        logger.info("Request received to get all movies");
        //Calling the service
        List<Movies> movies = movieService.getAllMovies();
        //Handling response
        logger.info("Request completed to get all movies");
        return ResponseEntity.ok(new Response<List<Movies>>(null, ApplicationConstants.MOVIE_RETRIEVED, true, movies));
    }


    @PutMapping
    ResponseEntity<Response<Movies>> updateMovie(@RequestBody @Valid Movie movie) throws InvalidDataException {
        logger.info("Request received to update movie {}", movie.getId());
        if (movie.getId() == null || movie.getId().isBlank()) {
            throw new InvalidDataException(ApplicationConstants.MOVIE_NOT_FOUND);
        }
        //Calling the service
        Movies movies = movieService.updateMovie(movie);
        //Handling response
        logger.info("Request completed to update movie {}", movie.getId());
        return ResponseEntity.ok(new Response<Movies>(null, ApplicationConstants.MOVIE_UPDATED, true, movies));
    }

    @DeleteMapping("/{movieId}")
    ResponseEntity<Response<String>> deleteMovie(@PathVariable @NotNull String movieId) throws InvalidDataException {
        logger.info("Request received to delete movie with id {}", movieId);
        //Calling the service
        movieService.deleteMovie(movieId);
        //Handling response
        logger.info("Request completed to delete movie with id {}", movieId);
        return ResponseEntity.ok(new Response<String>(null, ApplicationConstants.MOVIE_DELETED, true, null));
    }

}
