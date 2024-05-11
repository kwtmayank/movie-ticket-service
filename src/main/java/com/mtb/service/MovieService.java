package com.mtb.service;

import com.mtb.entity.Movies;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Movie;
import com.mtb.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreService genreService;


    public List<Movies> getAllMovies() {
        List<Movies> MoviesList = new ArrayList<Movies>();
        movieRepository.findAll().iterator().forEachRemaining(MoviesList::add);
        return MoviesList;
    }

    public Movies createMovie(Movie movie) throws InvalidDataException {
        Movies newMovie = new Movies();
        newMovie.setTitle(movie.getTitle());
        newMovie.setStarring(movie.getStarring());
        newMovie.setDescription(movie.getDescription());
        newMovie.setDuration(movie.getDuration());
        newMovie.setRating(movie.getRating());
        newMovie.setReleaseDate(movie.getReleaseDate());
        newMovie.setGenre(genreService.getGenre(movie.getGenre()).getGenreId());
        newMovie.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        newMovie.setInsertTs(Instant.now());
        newMovie.setUpdateTs(Instant.now());
        return movieRepository.save(newMovie);
    }

    public Movies getMovie(String movieId) throws InvalidDataException {
        Optional<Movies> Movies = Optional.ofNullable(movieRepository.findById(movieId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.MOVIE_NOT_FOUND)));
        return Movies.get();
    }

    public void deleteMovie(String movieId) throws InvalidDataException {
        movieRepository.delete(this.getMovie(movieId));
    }

    public Movies updateMovie(Movie movie) throws InvalidDataException {
        Movies Movies = this.getMovie(movie.getId());
        Movies.setStarring(movie.getStarring());
        Movies.setRating(movie.getRating());
        Movies.setReleaseDate(movie.getReleaseDate());
        Movies.setDescription(movie.getDescription());
        Movies.setTitle(movie.getTitle());
        Movies.setDuration(movie.getDuration());
        Movies.setGenre(genreService.getGenre(movie.getGenre()).getGenreId());
        Movies.setUpdateTs(Instant.now());
        Movies.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        return movieRepository.save(Movies);
    }
}
