package com.localsearch.localsearchapi.controller.places;

import com.localsearch.localsearchapi.constants.WeekDays;

import java.util.List;
import java.util.TreeMap;

public record PlaceDTO(String name, String location, TreeMap<WeekDays, List<String>> days){
}
