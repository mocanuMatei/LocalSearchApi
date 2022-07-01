package com.localsearch.localsearchapi.accessor.places;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record OpeningHours(HashMap<String, List<TimeSlot>> days, Boolean closedOnHolidays, Boolean openByArrangement) {

    @JsonCreator
    public OpeningHours(@JsonProperty("days") HashMap<String, List<TimeSlot>> days,
                        @JsonProperty("closed_on_holidays") Boolean closedOnHolidays,
                        @JsonProperty("open_by_arrangement") Boolean openByArrangement) {
        this.days = days;
        this.closedOnHolidays = closedOnHolidays;
        this.openByArrangement = openByArrangement;
    }
}
