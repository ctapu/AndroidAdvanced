package com.wikitech.organizer.presentation.locations;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wikitech.organizer.domain.location.FetchLocationItemsUseCase;
import com.wikitech.organizer.domain.location.LocationItem;

import java.util.List;

import timber.log.Timber;

public class LocationViewModel extends ViewModel {

    public static final String LOG_TAG = "Locations_tag";
    public ObservableArrayList<LocationItem> locations = new ObservableArrayList<>();

    public LocationViewModel(FetchLocationItemsUseCase fetchItemsUseCase){
        Timber.d("Locations displayed");

        LiveData<List<LocationItem>> liveItems = fetchItemsUseCase.execute();
        liveItems.observeForever(locationItems -> locations.addAll(locationItems));
    }
}