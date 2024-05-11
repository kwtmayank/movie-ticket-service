package com.mtb.service;

import com.mtb.entity.Theatres;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Theatre;
import com.mtb.repository.TheatreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    Logger logger = LoggerFactory.getLogger(TheatreService.class);

    @Autowired
    private TheatreRepository theatreRepository;

    public Theatres createTheatre(Theatre theatre) {
        Theatres newTheatre = new Theatres();
        newTheatre.setTheatreName(theatre.getName());
        newTheatre.setAddress(theatre.getAddress());
        newTheatre.setCity(theatre.getCity());
        newTheatre.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        newTheatre.setInsertTs(Instant.now());
        newTheatre.setUpdateTs(Instant.now());
        return theatreRepository.save(newTheatre);
    }

    public List<Theatres> getAllTheatres() {
        List<Theatres> theatresList = new ArrayList<>();
        theatreRepository.findAll().forEach(theatresList::add);
        return theatresList;
    }

    public Theatres updateTheatre(Theatre theatre) throws InvalidDataException {
        Theatres theatres = this.getTheatre(theatre.getCode());
        theatres.setTheatreName(theatre.getName());
        theatres.setCity(theatre.getCity());
        theatres.setAddress(theatre.getAddress());
        theatres.setUpdateTs(Instant.now());
        return theatreRepository.save(theatres);
    }

    public void deleteTheatre(String theatreId) throws InvalidDataException {
        Theatres theatres = this.getTheatre(theatreId);
        theatreRepository.delete(theatres);
    }

    public Theatres getTheatre(String theatreId) throws InvalidDataException {
        Optional<Theatres> theatres = Optional.ofNullable(theatreRepository.findById(theatreId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.THEATRE_NOT_FOUND)));
        return theatres.get();
    }

}
