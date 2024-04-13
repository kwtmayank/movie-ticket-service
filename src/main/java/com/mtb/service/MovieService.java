package com.mtb.service;

import com.mtb.entity.MovieDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Movie;
import com.mtb.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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


    public List<MovieDetails> getAllMovies() {
        List<MovieDetails> movieDetailsList = new ArrayList<MovieDetails>();
        movieRepository.findAll().iterator().forEachRemaining(movieDetailsList::add);
        return movieDetailsList;
    }

    public MovieDetails createMovie(Movie movie) throws InvalidDataException {
        MovieDetails newMovie = new MovieDetails();
        newMovie.setTitle(movie.getTitle());
        newMovie.setStarring(movie.getStarring());
        newMovie.setDescription(movie.getDescription());
        newMovie.setDuration(movie.getDuration());
        newMovie.setRating(movie.getRating());
        newMovie.setReleaseDate(movie.getReleaseDate());
        newMovie.setGenre(genreService.getGenre(movie.getGenre()));
        newMovie.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        newMovie.setInsertTs(OffsetDateTime.now());
        newMovie.setUpdateTs(OffsetDateTime.now());
        return movieRepository.save(newMovie);
    }

    public MovieDetails getMovie(String movieId) throws InvalidDataException {
        Optional<MovieDetails> movieDetails = Optional.ofNullable(movieRepository.findById(movieId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.MOVIE_NOT_FOUND)));
        return movieDetails.get();
    }

    public void deleteMovie(String movieId) throws InvalidDataException {
        Optional<MovieDetails> movieDetails = Optional.ofNullable(movieRepository.findById(movieId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.THEATRE_NOT_FOUND)));
        movieRepository.delete(movieDetails.get());
    }

    public MovieDetails updateMovie(Movie movie) throws InvalidDataException {
        Optional<MovieDetails> movieDetails = Optional.ofNullable(movieRepository.findById(movie.getId())
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.MOVIE_NOT_FOUND)));
        movieDetails.get().setStarring(movie.getStarring());
        movieDetails.get().setRating(movie.getRating());
        movieDetails.get().setReleaseDate(movie.getReleaseDate());
        movieDetails.get().setDescription(movie.getDescription());
        movieDetails.get().setTitle(movie.getTitle());
        movieDetails.get().setDuration(movie.getDuration());
        movieDetails.get().setGenre(genreService.getGenre(movie.getGenre()));
        movieDetails.get().setUpdateTs(OffsetDateTime.now());
        movieDetails.get().setUpdateUser(ApplicationConstants.SYSTEM_USER);
        movieRepository.save(movieDetails.get());
        return movieDetails.get();
    }
}
