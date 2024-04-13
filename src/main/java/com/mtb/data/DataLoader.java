package com.mtb.data;

import com.mtb.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataLoader {
    @Autowired
    private GenreService genreService;

    @Autowired
    private Map<String, String> genreList;


    @EventListener
    public void appReady(ApplicationReadyEvent applicationReadyEvent) {
        genreList.forEach((id, name) -> {
            genreService.createGenre(id, name);
        });


    }
}
