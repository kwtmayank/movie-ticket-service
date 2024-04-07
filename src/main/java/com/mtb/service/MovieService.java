package com.mtb.service;

import com.mtb.entity.MovieDetails;
import com.mtb.model.ApplicationConstants;
import com.mtb.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;


    public List<MovieDetails> getAllMovies() {
        List<MovieDetails> movieDetailsList = new ArrayList<MovieDetails>();
        movieRepository.findAll().iterator().forEachRemaining(movieDetailsList::add);
        return movieDetailsList;
    }

    public MovieDetails createMovie(MovieDetails movie) {
        movie.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        movie.setInsertTs(OffsetDateTime.now());
        movie.setUpdateTs(OffsetDateTime.now());
        return movieRepository.save(movie);
    }
}
