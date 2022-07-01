package com.localsearch.localsearchapi.constants;

public enum WeekDays {

    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private final String value;

    WeekDays(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
