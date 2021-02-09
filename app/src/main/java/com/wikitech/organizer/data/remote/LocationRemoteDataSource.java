package com.wikitech.organizer.data.remote;

import android.util.Log;

import com.wikitech.organizer.data.remote.api.LocationApi;
import com.wikitech.organizer.data.model.LocationItemDto;
import com.wikitech.organizer.domain.LocationItemsRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class LocationRemoteDataSource implements LocationItemsRepository {
    private static final String TAG = "remote-source";
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
            Log.w(TAG, "Something went wrong", e);
            return Collections.emptyList();
        }
    }
}
