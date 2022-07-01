package com.localsearch.localsearchapi.accessor.places;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaceResponse(String name, String location, OpeningHours openingHours) {

    @JsonCreator
    public PlaceResponse(@JsonProperty("displayed_what") String name,
                         @JsonProperty("displayed_where") String location,
                         @JsonProperty("opening_hours") OpeningHours openingHours) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
    }
}
