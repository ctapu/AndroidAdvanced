package com.wikitech.organizer.data.remote;

import android.util.Log;

import com.wikitech.organizer.data.model.PersonItemDto;
import com.wikitech.organizer.data.remote.api.PeopleApi;
import com.wikitech.organizer.domain.people.PersonItemsRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class PersonRemoteDataSource implements PersonItemsRepository {
    private static final String TAG = "people-remote-source";
    private final PeopleApi api;

    public PersonRemoteDataSource(PeopleApi api) {
        this.api = api;
    }

    @Override
    public List<PersonItemDto> getItems() {
        try {
            Response<List<PersonItemDto>> response = api.getItems().execute();
            return response.body();
        } catch (IOException e) {
            Log.w(TAG, "Something went wrong", e);
            return Collections.emptyList();
        }
    }
}
