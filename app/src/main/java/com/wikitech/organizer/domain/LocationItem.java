package com.wikitech.organizer.domain;

public class LocationItem {
    private final String name;
    private final String description;

    public LocationItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
