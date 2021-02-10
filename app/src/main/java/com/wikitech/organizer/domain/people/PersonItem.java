package com.wikitech.organizer.domain.people;

import java.time.LocalDate;

public class PersonItem {
    private final String name;
    private final LocalDate birthDate;

    public PersonItem(String name, LocalDate birthDate) {
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
