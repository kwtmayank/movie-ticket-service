package com.mtb.service;

import com.mtb.entity.TheatreDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.request.Theatre;
import com.mtb.repository.TheatreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    Logger logger = LoggerFactory.getLogger(TheatreService.class);

    @Autowired
    private TheatreRepository theatreRepository;

    public TheatreDetails createTheatre(Theatre theatre) {
        TheatreDetails newTheatre = new TheatreDetails();
        newTheatre.setTheatreName(theatre.getName());
        newTheatre.setAddress(theatre.getAddress());
        newTheatre.setCity(theatre.getCity());
        newTheatre.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        newTheatre.setInsertTs(OffsetDateTime.now());
        newTheatre.setUpdateTs(OffsetDateTime.now());
        return theatreRepository.save(newTheatre);
    }

    public List<TheatreDetails> getAllTheatres() {
        List<TheatreDetails> theatreDetailsList = new ArrayList<TheatreDetails>();
        theatreRepository.findAll().forEach(theatreDetailsList::add);
        return theatreDetailsList;
    }

    public TheatreDetails updateTheatre(Theatre theatre) throws InvalidDataException {
        TheatreDetails theatreDetails = this.getTheatre(theatre.getCode());
        theatreDetails.setTheatreName(theatre.getName());
        theatreDetails.setCity(theatre.getCity());
        theatreDetails.setAddress(theatre.getAddress());
        theatreDetails.setUpdateTs(OffsetDateTime.now());
        return theatreRepository.save(theatreDetails);
    }

    public void deleteTheatre(String theatreId) throws InvalidDataException {
        TheatreDetails theatreDetails = this.getTheatre(theatreId);
        theatreRepository.delete(theatreDetails);
    }

    public TheatreDetails getTheatre(String theatreId) throws InvalidDataException {
        Optional<TheatreDetails> theatreDetails = Optional.ofNullable(theatreRepository.findById(theatreId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.THEATRE_NOT_FOUND)));
        return theatreDetails.get();
    }

}
