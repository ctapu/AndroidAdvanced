package com.wikitech.organizer.domain.location;

import com.wikitech.organizer.data.model.LocationItemDto;

import java.util.List;

public interface LocationItemsRepository {
    List<LocationItemDto> getItems();
}
