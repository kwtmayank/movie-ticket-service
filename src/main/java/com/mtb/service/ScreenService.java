package com.mtb.service;

import com.mtb.entity.Screens;
import com.mtb.entity.Theatres;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Screen;
import com.mtb.repository.ScreenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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


    public Screens getScreen(String screenId) throws InvalidDataException {
        Optional<Screens> screens = Optional.ofNullable(screenRepository.findById(screenId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_SCREEN)));
        return screens.get();
    }

    public Screens createScreen(Screen screen) throws InvalidDataException {
        Screens screens = new Screens();
        Theatres theatres = theatreService.getTheatre(screen.getTheatreCode());
        screens.setTheatre(theatres.getTheatreId());
        screens.setCapacity(screen.getCapacity());
        screens.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        screens.setInsertTs(Instant.now());
        screens.setUpdateTs(Instant.now());
        return screenRepository.save(screens);
    }

    public Screens updateScreen(Screen screen) throws InvalidDataException {
        Screens screens = this.getScreen(screen.getScreenId());
        screens.setCapacity(screen.getCapacity());
        Theatres theatres = theatreService.getTheatre(screen.getTheatreCode());
        screens.setTheatre(theatres.getTheatreId());
        screens.setUpdateTs(Instant.now());
        return screenRepository.save(screens);
    }

    public void deleteScreen(String screenId) throws InvalidDataException {
        Screens screens = this.getScreen(screenId);
        screenRepository.delete(screens);
    }

    public Screens getScreenForTheatre(String theatreId) throws InvalidDataException {
        Theatres theatreDetails = theatreService.getTheatre(theatreId);
        Optional<Screens> screensOptional = screenRepository.findByTheatre(
                theatreDetails);
        return screensOptional.orElse(null);
    }

    public List<Screens> getAllScreens() {
        List<Screens> screensList = new ArrayList<Screens>();
        screenRepository.findAll().forEach(screensList::add);
        return screensList;
    }


}
