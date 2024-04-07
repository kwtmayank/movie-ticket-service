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

    public TheatreDetails createTheatre(TheatreDetails theatre) {
        theatre.setUpdateUser(ApplicationConstants.SYSTEM_USER);
        theatre.setInsertTs(OffsetDateTime.now());
        theatre.setUpdateTs(OffsetDateTime.now());
        return theatreRepository.save(theatre);
    }

    public List<TheatreDetails> getAllTheatres() {
        List<TheatreDetails> theatreDetailsList = new ArrayList<TheatreDetails>();
        theatreRepository.findAll().forEach(theatreDetailsList::add);
        return theatreDetailsList;
    }

    public TheatreDetails updateTheatre(Theatre theatre) throws InvalidDataException {
        Optional<TheatreDetails> theatreDetails = Optional.ofNullable(theatreRepository.findById(theatre.getCode())
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.THEATRE_NOT_FOUND)));
        theatreDetails.get().setTheatreName(theatre.getName());
        theatreDetails.get().setCity(theatre.getCity());
        theatreDetails.get().setAddress(theatre.getAddress());
        theatreDetails.get().setUpdateTs(OffsetDateTime.now());
        theatreRepository.save(theatreDetails.get());
        return theatreDetails.get();

    }

    public void deleteTheatre(String theatreId) throws InvalidDataException {
        Optional<TheatreDetails> theatreDetails = Optional.ofNullable(theatreRepository.findById(theatreId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.THEATRE_NOT_FOUND)));
        theatreRepository.delete(theatreDetails.get());
    }

    public TheatreDetails getTheatre(String theatreId) throws InvalidDataException {
        Optional<TheatreDetails> theatreDetails = Optional.ofNullable(theatreRepository.findById(theatreId)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.THEATRE_NOT_FOUND)));
        return theatreDetails.get();
    }


}
