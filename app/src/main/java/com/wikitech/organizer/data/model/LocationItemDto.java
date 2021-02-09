package com.wikitech.organizer.data.model;

import com.google.gson.annotations.SerializedName;

public class LocationItemDto {
    @SerializedName("name")
    private final String name;
    @SerializedName("description")
    private final String description;

    public LocationItemDto(String name, String description) {
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
