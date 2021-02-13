package com.wikitech.organizer.data.remote;

import android.util.Log;

import com.wikitech.organizer.data.remote.api.LocationApi;
import com.wikitech.organizer.data.model.LocationItemDto;
import com.wikitech.organizer.domain.location.LocationItemsRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

public class LocationRemoteDataSource implements LocationItemsRepository {
    private static final String TAG = "location-remote-source";
    private final LocationApi api;

    public LocationRemoteDataSource(LocationApi api) {
        this.api = api;
    }

    @Override
    public List<LocationItemDto> getItems() {
        try {
            Response<List<LocationItemDto>> response = api.getItems().execute();
            return response.body();
        } catch (IOException e) {
            Timber.tag(TAG).w(e, "Something went wrong");
            return Collections.emptyList();
        }
    }
}
