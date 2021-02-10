package com.wikitech.organizer.presentation.locations;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wikitech.organizer.domain.FetchLocationItemsUseCase;
import com.wikitech.organizer.domain.LocationItem;

import java.util.List;

public class LocationViewModel extends ViewModel {

    public static final String LOG_TAG = "Locations_tag";
    public ObservableArrayList<LocationItem> locations = new ObservableArrayList<>();

    public LocationViewModel(FetchLocationItemsUseCase fetchItemsUseCase){
        Log.d(LOG_TAG, "Locations displayed");

        LiveData<List<LocationItem>> liveItems = fetchItemsUseCase.execute();
        liveItems.observeForever(locationItems -> locations.addAll(locationItems));
    }
}