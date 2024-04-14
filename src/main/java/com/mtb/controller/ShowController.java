package com.mtb.controller;

import com.mtb.entity.ShowDetails;
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
    ResponseEntity<Response<ShowDetails>> createShow(@RequestBody @Valid Show request) throws InvalidDataException {
        logger.info("Request received to create show {}", request.toString());
        //Calling the service
        ShowDetails showDetails = showService.createShow(request);
        //Handling response
        logger.info("Request completed to create show with {}", showDetails.getScreenId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_CREATED, true, showDetails));
    }

    @GetMapping("/{showId}")
    ResponseEntity<Response<ShowDetails>> getShow(@PathVariable @NotNull String showId) throws InvalidDataException {
        logger.info("Request received to get show with id {}", showId);
        //Calling the service
        ShowDetails showDetails = showService.getShow(showId);
        //Handling response
        logger.info("Request completed to get show with id {}", showId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_RETRIEVED, true, showDetails));
    }

    @GetMapping
    ResponseEntity<Response<List<ShowDetails>>> getAllShows() {
        logger.info("Request received to get all shows");
        //Calling the service
        List<ShowDetails> showDetails = showService.getAllShows();
        //Handling response
        logger.info("Request completed to get all shows");
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SHOW_RETRIEVED, true, showDetails));
    }


    @PutMapping
    ResponseEntity<Response<ShowDetails>> updateShow(@RequestBody @Valid Show show) throws InvalidDataException {
        logger.info("Request received to update show {}", show.getShowId());
        if (show.getShowId() == null || show.getShowId().isBlank()) {
            throw new InvalidDataException(ApplicationConstants.INVALID_SHOW);
        }
        //Calling the service
        ShowDetails updatedShow = showService.updateShow(show);
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
