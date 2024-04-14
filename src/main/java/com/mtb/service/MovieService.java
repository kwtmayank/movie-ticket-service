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
        movieRepository.delete(this.getMovie(movieId));
    }

    public MovieDetails updateMovie(Movie movie) throws InvalidDataException {
        MovieDetails movieDetails = this.getMovie(movie.getId());
        movieDetails.setStarring(movie.getStarring());
        movieDetails.setRating(movie.getRating());
        movieDetails.setReleaseDate(movie.getReleaseDate());
        movieDetails.setDescription(movie.getDescription());
        movieDetails.setTitle(movie.getTitle());
        movieDetails.setDuration(movie.getDuration());
        movieDetails.setGenre(genreService.getGenre(movie.getGenre()));
        movieDetails.setUpdateTs(OffsetDateTime.now());
        movieDetails.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        return movieRepository.save(movieDetails);
    }
}
