package com.mtb.controller;

import com.mtb.entity.Shows;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.Show;
import com.mtb.service.ShowService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/show")
public class ShowController {
    Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    ShowService showService;

    @PostMapping
    ResponseEntity<Response<Shows>> createShow(@RequestBody @Valid Show request) throws InvalidDataException {
        logger.info("Request received to create show {}", request.toString());
        //Calling the service
        Shows shows = showService.createShow(request);
        //Handling response
        logger.info("Request completed to create show with {}", shows.getScreenId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_CREATED, true, shows));
    }

    @GetMapping("/{showId}")
    ResponseEntity<Response<Shows>> getShow(@PathVariable @NotNull String showId) throws InvalidDataException {
        logger.info("Request received to get show with id {}", showId);
        //Calling the service
        Shows shows = showService.getShow(showId);
        //Handling response
        logger.info("Request completed to get show with id {}", showId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_RETRIEVED, true, shows));
    }

    @GetMapping
    ResponseEntity<Response<List<Shows>>> getAllShows() {
        logger.info("Request received to get all shows");
        //Calling the service
        List<Shows> shows = showService.getAllShows();
        //Handling response
        logger.info("Request completed to get all shows");
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_RETRIEVED, true, shows));
    }


    @PutMapping
    ResponseEntity<Response<Shows>> updateShow(@RequestBody @Valid Show show) throws InvalidDataException {
        logger.info("Request received to update show {}", show.getShowId());
        if (show.getShowId() == null || show.getShowId().isBlank()) {
            throw new InvalidDataException(ApplicationConstants.INVALID_SHOW);
        }
        //Calling the service
        Shows updatedShow = showService.updateShow(show);
        //Handling response
        logger.info("Request completed to update show {}", show.getShowId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_UPDATED, true, updatedShow));
    }

    @DeleteMapping("/{showId}")
    ResponseEntity<Response<String>> deleteShow(@PathVariable @NotNull String showId) throws InvalidDataException {
        logger.info("Request received to delete show with id {}", showId);
        //Calling the servicesho
        showService.deleteShow(showId);
        //Handling response
        logger.info("Request completed to delete show with id {}", showId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_DELETED, true, null));
    }

}
