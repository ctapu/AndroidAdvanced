package com.wikitech.organizer.data.local;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.wikitech.organizer.data.model.PersonItemDto;
import com.wikitech.organizer.domain.people.PersonItemsRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PersonInMemoryDataSource implements PersonItemsRepository {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<PersonItemDto> getItems() {
        List<PersonItemDto> locationItemDtos = Arrays.asList(
                new PersonItemDto("My name", LocalDate.of(1996, 9, 14)),
                new PersonItemDto("Her name", LocalDate.of(2000, 10, 10))
        );
        return locationItemDtos;
    }
}
