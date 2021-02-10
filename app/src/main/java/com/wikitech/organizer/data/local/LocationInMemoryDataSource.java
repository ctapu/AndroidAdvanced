package com.wikitech.organizer.data.local;

import com.wikitech.organizer.data.model.LocationItemDto;
import com.wikitech.organizer.domain.location.LocationItemsRepository;

import java.util.Arrays;
import java.util.List;

public class LocationInMemoryDataSource implements LocationItemsRepository {

    @Override
    public List<LocationItemDto> getItems() {
        List<LocationItemDto> locationItemDtos = Arrays.asList(
                new LocationItemDto("Home", "Main street, no. 23, Bucharest, Romania"),
                new LocationItemDto("Work", "Not that main street, no. 55, Bucharest, Romania")
        );
        return locationItemDtos;
    }
}
