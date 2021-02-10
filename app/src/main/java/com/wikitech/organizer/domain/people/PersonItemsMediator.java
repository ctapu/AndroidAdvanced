package com.wikitech.organizer.domain.people;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wikitech.organizer.data.model.PersonItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonItemsMediator {
    private final PersonItemsRepository localRepository;
    private final PersonItemsRepository remoteRepository;
    private final ExecutorService executorService;
    private final MutableLiveData<List<PersonItem>> liveItems;

    public PersonItemsMediator(PersonItemsRepository localRepository,
                               PersonItemsRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.executorService = Executors.newSingleThreadExecutor();
        this.liveItems = new MutableLiveData<>();
    }

    public LiveData<List<PersonItem>> getItems() {
        ArrayList<PersonItem> items = new ArrayList<>();
        if (localRepository.getItems().size() > 0) {
            items.addAll(getItemsFromRepository(localRepository));
        } else {
            executorService.execute(() -> items.addAll(getItemsFromRepository(remoteRepository)));
        }
        liveItems.postValue(items);
        return liveItems;
    }

    private ArrayList<PersonItem> getItemsFromRepository(PersonItemsRepository repository) {
        ArrayList<PersonItem> items = new ArrayList<>();
        for (PersonItemDto dto : repository.getItems()) {
            items.add(new PersonItem(dto.getName(), dto.getbirthDate()));
        }
        return items;
    }
}
