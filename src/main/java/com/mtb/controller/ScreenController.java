package com.mtb.controller;

import com.mtb.entity.Screens;
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
    ResponseEntity<Response<Screens>> createScreen(@RequestBody @Valid Screen request) throws InvalidDataException {
        logger.info("Request received to create screen {}", request.toString());
        //Calling the service
        Screens screens = screenService.createScreen(request);
        //Handling response
        logger.info("Request completed to create screen with {}", screens.getScreenId());
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_CREATED, true, screens));
    }

    @GetMapping("/{screenId}")
    ResponseEntity<Response<Screens>> getScreen(@PathVariable @NotNull String screenId) throws InvalidDataException {
        logger.info("Request received to get screen with id {}", screenId);
        //Calling the service
        Screens screens = screenService.getScreen(screenId);
        //Handling response
        logger.info("Request completed to get screen with id {}", screenId);
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_RETRIEVED, true, screens));
    }

    @GetMapping
    ResponseEntity<Response<List<Screens>>> getAllScreens() {
        logger.info("Request received to get all screens");
        //Calling the service
        List<Screens> screens = screenService.getAllScreens();
        //Handling response
        logger.info("Request completed to get all screens");
        return ResponseEntity.ok(new Response<>(null, ApplicationConstants.SCREEN_RETRIEVED, true, screens));
    }


    @PutMapping
    ResponseEntity<Response<Screens>> updateScreen(@RequestBody @Valid Screen screen) throws InvalidDataException {
        logger.info("Request received to update screen {}", screen.getScreenId());
        if (screen.getScreenId() == null || screen.getScreenId().isBlank()) {
            throw new InvalidDataException(ApplicationConstants.SCREEN_NOT_FOUND);
        }
        //Calling the service
        Screens updatedScreen = screenService.updateScreen(screen);
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
