package com.mtb.service;

import com.mtb.entity.ScreenDetails;
import com.mtb.entity.ShowDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Show;
import com.mtb.repository.ShowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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


    public ShowDetails getShow(String showId) throws InvalidDataException {
        Optional<ShowDetails> showDetails = Optional.ofNullable(showRepository.findById(showId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_SHOW)));
        return showDetails.orElse(null);
    }

    public ShowDetails createShow(Show show) throws InvalidDataException {
        ShowDetails showDetails = new ShowDetails();
        showDetails.setMovieId(movieService.getMovie(show.getMovieId()));
        ScreenDetails screenDetails = screenService.getScreen(show.getScreenId());
        showDetails.setScreenId(screenDetails);
        showDetails.setAvailableSeats(screenDetails.getCapacity());
        showDetails.setShowTiming(show.getShowTime());
        showDetails.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        showDetails.setInsertTs(OffsetDateTime.now());
        showDetails.setUpdateTs(OffsetDateTime.now());
        return showRepository.save(showDetails);
    }

    public ShowDetails updateShow(Show show) throws InvalidDataException {
        ShowDetails showDetails = this.getShow(show.getShowId());
        showDetails.setMovieId(movieService.getMovie(show.getMovieId()));
        ScreenDetails screenDetails = screenService.getScreen(show.getScreenId());
        showDetails.setScreenId(screenDetails);
        showDetails.setAvailableSeats(screenDetails.getCapacity());
        showDetails.setUpdateTs(OffsetDateTime.now());
        return showRepository.save(showDetails);
    }

    public void deleteShow(String showId) throws InvalidDataException {
        showRepository.delete(this.getShow(showId));
    }

    public List<ShowDetails> getAllShows() {
        List<ShowDetails> showDetails = new ArrayList<ShowDetails>();
        showRepository.findAll().forEach(showDetails::add);
        return showDetails;
    }


}
