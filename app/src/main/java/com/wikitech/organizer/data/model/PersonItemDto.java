package com.wikitech.organizer.data.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class PersonItemDto {
    @SerializedName("name")
    private final String name;
    @SerializedName("birthDate")
    private final LocalDate birthDate;

    public PersonItemDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getbirthDate() {
        return birthDate;
    }
}
