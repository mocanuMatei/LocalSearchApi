package com.localsearch.localsearchapi.accessor.places;

import com.localsearch.localsearchapi.accessor.AccessorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class PlacesAccessor {

    private static final Logger logger = LoggerFactory.getLogger(PlacesAccessor.class);

    private final WebClient placesWebClient;
    private final PlacesConfiguration placesConfiguration;

    public PlacesAccessor(WebClient placesWebClient, PlacesConfiguration placesConfiguration) {
        this.placesWebClient = placesWebClient;
        this.placesConfiguration = placesConfiguration;
    }

    public PlaceResponse getPlaceById(String placeId) {
        logger.info("Sending get place by id request, id = {}", placeId);
        try {
            return placesWebClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(placesConfiguration.codingSessionPath())
                            .build(placeId))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, this::convertToException)
                    .onStatus(HttpStatus::is5xxServerError, this::convertToException)
                    .bodyToMono(PlaceResponse.class)
                    .doOnNext(placeResponse -> logger.info("Place with id {} was retrieved successfully: {}", placeId, placeResponse))
                    .block();
        } catch (Exception ex) {
            if (ex instanceof AccessorException accessorException) {
                if(accessorException.getHttpStatus() == HttpStatus.NOT_FOUND){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
                }
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    private Mono<Throwable> convertToException(ClientResponse response) {
        return response.bodyToMono(String.class)
                .defaultIfEmpty("Unknown message")
                .flatMap(errorMessage -> Mono.error(new AccessorException("Status code from the source system: " + response.statusCode() + ", error message: " + errorMessage, response.statusCode())));
    }
}
