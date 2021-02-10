package com.wikitech.organizer.domain.location;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FetchLocationItemsUseCase {
    private final LocationItemsMediator mediator;

    public FetchLocationItemsUseCase(LocationItemsMediator mediator) {
        this.mediator = mediator;
    }

    public LiveData<List<LocationItem>> execute() {
        return mediator.getItems();
    }
}
