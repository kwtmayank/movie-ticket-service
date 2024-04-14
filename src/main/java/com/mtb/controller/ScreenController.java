package com.mtb.controller;

import com.mtb.entity.ScreenDetails;
import com.mtb.exception.InvalidDataException;
import com.mtb.model.ApplicationConstants;
import com.mtb.model.Response;
import com.mtb.model.request.Screen;
import com.mtb.service.ScreenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/screen")
public class ScreenController {
    Logger logger = LoggerFactory.getLogger(ScreenController.class);

    @Autowired
    ScreenService screenService;

    @PostMapping
    ResponseEntity<Response<ScreenDetails>> createScreen(@RequestBody @Valid Screen request) throws InvalidDataException {
        logger.info("Request received to create screen {}", request.toString());
        //Calling the service
        ScreenDetails screenDetails = screenService.createScreen(request);
        //Handling response
        logger.info("Request completed to create screen with {}", screenDetails.getScreenId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_CREATED, true, screenDetails));
    }

    @GetMapping("/{screenId}")
    ResponseEntity<Response<ScreenDetails>> getScreen(@PathVariable @NotNull String screenId) throws InvalidDataException {
        logger.info("Request received to get screen with id {}", screenId);
        //Calling the service
        ScreenDetails screenDetails = screenService.getScreen(screenId);
        //Handling response
        logger.info("Request completed to get screen with id {}", screenId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_RETRIEVED, true, screenDetails));
    }

    @GetMapping
    ResponseEntity<Response<List<ScreenDetails>>> getAllScreens() {
        logger.info("Request received to get all screens");
        //Calling the service
        List<ScreenDetails> screenDetails = screenService.getAllScreens();
        //Handling response
        logger.info("Request completed to get all screens");
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_RETRIEVED, true, screenDetails));
    }


    @PutMapping
    ResponseEntity<Response<ScreenDetails>> updateScreen(@RequestBody @Valid Screen screen) throws InvalidDataException {
        logger.info("Request received to update screen {}", screen.getScreenId());
        if (screen.getScreenId() == null || screen.getScreenId().isBlank()) {
            throw new InvalidDataException(ApplicationConstants.SCREEN_NOT_FOUND);
        }
        //Calling the service
        ScreenDetails updatedScreen = screenService.updateScreen(screen);
        //Handling response
        logger.info("Request completed to update screen {}", screen.getScreenId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_UPDATED, true, updatedScreen));
    }

    @DeleteMapping("/{screenId}")
    ResponseEntity<Response<String>> deleteScreen(@PathVariable @NotNull String screenId) throws InvalidDataException {
        logger.info("Request received to delete screen with id {}", screenId);
        //Calling the service
        screenService.deleteScreen(screenId);
        //Handling response
        logger.info("Request completed to delete screenId with id {}", screenId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_DELETED, true, null));
    }

}
