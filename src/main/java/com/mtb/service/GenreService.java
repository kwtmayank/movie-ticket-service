package com.mtb.service;

import com.mtb.entity.Genre;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    Logger logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private GenreRepository genreRepository;


    public Genre getGenre(String genreName) throws InvalidDataException {
        Optional<Genre> genre = Optional.ofNullable(genreRepository.findById(genreName)
                .orElseThrow(() -> new InvalidDataException(ApplicationConstants.INVALID_GENRE)));
        return genre.get();
    }

    public void createGenre(String id, String name) {
        genreRepository.save(new Genre(id, name));
    }
}
