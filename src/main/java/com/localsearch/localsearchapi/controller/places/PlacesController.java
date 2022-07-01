package com.localsearch.localsearchapi.controller.places;

import com.localsearch.localsearchapi.service.PlacesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/places")
public class PlacesController {

    private static final Logger logger = LoggerFactory.getLogger(PlacesController.class);

    private final PlacesService placesService;

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlace(@PathVariable String id) {
        logger.info("Received request to get place with id {}", id);
        PlaceDTO placeDTO = placesService.getPLaceById(id);
        logger.info("PlaceDTO with id {} was found {}", id, placeDTO);
        return ResponseEntity.ok(placeDTO);
    }
}
