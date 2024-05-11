package com.mtb.service;

import com.mtb.entity.Screens;
import com.mtb.entity.Shows;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Show;
import com.mtb.repository.ShowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    Logger logger = LoggerFactory.getLogger(ShowService.class);

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieService movieService;
    @Autowired
    private ScreenService screenService;


    public Shows getShow(String showId) throws InvalidDataException {
        Optional<Shows> shows = Optional.ofNullable(showRepository.findById(showId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_SHOW)));
        return shows.orElse(null);
    }

    public Shows createShow(Show show) throws InvalidDataException {
        Shows shows = new Shows();
        shows.setMovieId(movieService.getMovie(show.getMovieId()).getMovieId());
        Screens screenDetails = screenService.getScreen(show.getScreenId());
        shows.setScreenId(screenDetails.getScreenId());
        shows.setAvailableSeats(screenDetails.getCapacity());
        shows.setShowTiming(show.getShowTiming());
        shows.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        shows.setInsertTs(Instant.now());
        shows.setUpdateTs(Instant.now());
        return showRepository.save(shows);
    }

    public Shows updateShow(Show show) throws InvalidDataException {
        Shows shows = this.getShow(show.getShowId());
        shows.setMovieId(movieService.getMovie(show.getMovieId()).getMovieId());
        Screens screenDetails = screenService.getScreen(show.getScreenId());
        shows.setScreenId(screenDetails.getScreenId());
        shows.setAvailableSeats(screenDetails.getCapacity());
        shows.setUpdateTs(Instant.now());
        return showRepository.save(shows);
    }

    public void deleteShow(String showId) throws InvalidDataException {
        showRepository.delete(this.getShow(showId));
    }

    public List<Shows> getAllShows() {
        List<Shows> shows = new ArrayList<Shows>();
        showRepository.findAll().forEach(shows::add);
        return shows;
    }


}
