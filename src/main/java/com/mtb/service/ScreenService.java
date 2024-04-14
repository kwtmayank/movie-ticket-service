package com.mtb.service;

import com.mtb.entity.ScreenDetails;
import com.mtb.entity.TheatreDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Screen;
import com.mtb.repository.ScreenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenService {

    Logger logger = LoggerFactory.getLogger(ScreenService.class);

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreService theatreService;


    public ScreenDetails getScreen(String screenId) throws InvalidDataException {
        Optional<ScreenDetails> screenDetails = Optional.ofNullable(screenRepository.findById(screenId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_SCREEN)));
        return screenDetails.get();
    }

    public ScreenDetails createScreen(Screen screen) throws InvalidDataException {
        ScreenDetails screenDetails = new ScreenDetails();
        screenDetails.setTheatre(theatreService.getTheatre(screen.getTheatreCode()));
        screenDetails.setCapacity(screen.getCapacity());
        screenDetails.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        screenDetails.setInsertTs(OffsetDateTime.now());
        screenDetails.setUpdateTs(OffsetDateTime.now());
        return screenRepository.save(screenDetails);
    }

    public ScreenDetails updateScreen(Screen screen) throws InvalidDataException {
        ScreenDetails screenDetails = this.getScreen(screen.getScreenId());
        screenDetails.setCapacity(screen.getCapacity());
        screenDetails.setTheatre(theatreService.getTheatre(screen.getTheatreCode()));
        screenDetails.setUpdateTs(OffsetDateTime.now());
        return screenRepository.save(screenDetails);
    }

    public void deleteScreen(String screenId) throws InvalidDataException {
        ScreenDetails screenDetails = this.getScreen(screenId);
        screenRepository.delete(screenDetails);
    }

    public ScreenDetails getScreenForTheatre(String theatreId) throws InvalidDataException {
        TheatreDetails theatreDetails = theatreService.getTheatre(theatreId);
        Optional<ScreenDetails> screenDetailsOptional = screenRepository.findByTheatre(
                theatreDetails);
        return screenDetailsOptional.orElse(null);
    }

    public List<ScreenDetails> getAllScreens() {
        List<ScreenDetails> screenDetailsList = new ArrayList<ScreenDetails>();
        screenRepository.findAll().forEach(screenDetailsList::add);
        return screenDetailsList;
    }


}
