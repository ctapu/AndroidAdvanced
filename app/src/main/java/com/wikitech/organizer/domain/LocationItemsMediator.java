package com.wikitech.organizer.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wikitech.organizer.data.model.LocationItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocationItemsMediator {
    private final LocationItemsRepository localRepository;
    private final LocationItemsRepository remoteRepository;
    private final ExecutorService executorService;
    private final MutableLiveData<List<LocationItem>> liveItems;

    public LocationItemsMediator(LocationItemsRepository localRepository,
                                 LocationItemsRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.executorService = Executors.newSingleThreadExecutor();
        this.liveItems = new MutableLiveData<>();
    }

    public LiveData<List<LocationItem>> getItems() {
        ArrayList<LocationItem> items = new ArrayList<>();
        if (localRepository.getItems().size() > 0) {
            items.addAll(getItemsFromRepository(localRepository));
        } else {
            executorService.execute(() -> {
                items.addAll(getItemsFromRepository(remoteRepository));
            });
        }
        liveItems.postValue(items);
        return liveItems;
    }

    private ArrayList<LocationItem> getItemsFromRepository(LocationItemsRepository repository) {
        ArrayList<LocationItem> items = new ArrayList<>();
        for (LocationItemDto dto : repository.getItems()) {
            items.add(new LocationItem(dto.getName(), dto.getDescription()));
        }
        return items;
    }
}
