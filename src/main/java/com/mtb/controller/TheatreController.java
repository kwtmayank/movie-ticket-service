package com.mtb.controller;

import com.mtb.entity.Theatres;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.Theatre;
import com.mtb.service.TheatreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/theatre")
public class TheatreController {
    Logger logger = LoggerFactory.getLogger(TheatreController.class);

    @Autowired
    TheatreService theatreService;

    @PostMapping
    ResponseEntity<Response<Theatres>> createTheatre(@RequestBody @Valid Theatre request) {
        logger.info("Request received to create theatre {}", request.toString());
        //Calling the service
        Theatres theatres = theatreService.createTheatre(request);
        //Handling response
        logger.info("Request completed to create theatre with {}", theatres.getTheatreId());
        return ResponseEntity.ok(new Response<Theatres>(null, ApplicationConstants.THEATRE_CREATED, true, theatres));
    }

    @GetMapping("/{theatreId}")
    ResponseEntity<Response<Theatres>> getTheatre(@PathVariable @NotNull String theatreId) throws InvalidDataException {
        logger.info("Request received to get theatre with id {}", theatreId);
        //Calling the service
        Theatres theatres = theatreService.getTheatre(theatreId);
        //Handling response
        logger.info("Request completed to get theatre with id {}", theatreId);
        return ResponseEntity.ok(new Response<Theatres>(null, ApplicationConstants.THEATRE_RETRIEVED, true, theatres));
    }

    @GetMapping
    ResponseEntity<Response<List<Theatres>>> getAllTheaters() {
        logger.info("Request received to get all theatres");
        //Calling the service
        List<Theatres> theatres = theatreService.getAllTheatres();
        //Handling response
        logger.info("Request completed to get all theatres");
        return ResponseEntity.ok(new Response<List<Theatres>>(null, ApplicationConstants.THEATRE_RETRIEVED, true, theatres));
    }


    @PutMapping
    ResponseEntity<Response<Theatres>> updateTheatre(@RequestBody @Valid Theatre theatre) throws InvalidDataException {
        logger.info("Request received to update theatre {}", theatre.getCode());
        //Calling the service
        Theatres updatedTheatre = theatreService.updateTheatre(theatre);
        //Handling response
        logger.info("Request completed to update theatre {}", theatre.getCode());
        return ResponseEntity.ok(new Response<Theatres>(null, ApplicationConstants.THEATRE_UPDATED, true, updatedTheatre));
    }

    @DeleteMapping("/{theatreId}")
    ResponseEntity<Response<String>> deleteTheatre(@PathVariable @NotNull String theatreId) throws InvalidDataException {
        logger.info("Request received to delete theatre with id {}", theatreId);
        //Calling the service
        theatreService.deleteTheatre(theatreId);
        //Handling response
        logger.info("Request completed to delete theatre with id {}", theatreId);
        return ResponseEntity.ok(new Response<String>(null, ApplicationConstants.THEATRE_DELETED, true, null));
    }

}
