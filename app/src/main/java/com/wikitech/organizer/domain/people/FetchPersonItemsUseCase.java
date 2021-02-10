package com.wikitech.organizer.domain.people;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FetchPersonItemsUseCase {
    private final PersonItemsMediator mediator;

    public FetchPersonItemsUseCase(PersonItemsMediator mediator) {
        this.mediator = mediator;
    }

    public LiveData<List<PersonItem>> execute() {
        return mediator.getItems();
    }
}
