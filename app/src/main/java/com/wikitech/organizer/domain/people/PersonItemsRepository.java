package com.wikitech.organizer.domain.people;

import com.wikitech.organizer.data.model.PersonItemDto;

import java.util.List;

public interface PersonItemsRepository {
    List<PersonItemDto> getItems();
}
