package com.localsearch.localsearchapi.service;

import com.localsearch.localsearchapi.accessor.places.*;
import com.localsearch.localsearchapi.constants.WeekDays;
import com.localsearch.localsearchapi.controller.places.PlaceDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlacesService {

    private final PlacesAccessor placesAccessor;

    public PlacesService(PlacesAccessor placesAccessor) {
        this.placesAccessor = placesAccessor;
    }

    public PlaceDTO getPLaceById(String placeId) {
        PlaceResponse placeResponse = placesAccessor.getPlaceById(placeId);
        return prepareData(placeResponse);
    }

    private PlaceDTO prepareData(PlaceResponse placeResponse) {
        TreeMap<WeekDays, List<String>> days = new TreeMap<>();
        HashMap<String, List<TimeSlot>> responseDays = placeResponse.openingHours().days();

        Arrays.stream(WeekDays.values())
                .forEach(weekDay -> {

                    if (responseDays.containsKey(weekDay.getValue()) && !responseDays.get(weekDay.getValue()).isEmpty()) {
                        days.put(weekDay, new ArrayList<>());
                        responseDays.get(weekDay.getValue()).forEach(timeSlot -> {
                            if (timeSlot.type() == OpenType.OPEN) {
                                days.get(weekDay).add(timeSlot.start() + "-" + timeSlot.end());
                            }
                        });

                    } else {
                        days.put(weekDay, null);
                    }

                });

        return new PlaceDTO(placeResponse.name(), placeResponse.location(), days);
    }
}
