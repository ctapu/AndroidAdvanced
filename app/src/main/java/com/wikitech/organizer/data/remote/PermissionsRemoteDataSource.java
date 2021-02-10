package com.wikitech.organizer.data.remote;

import com.wikitech.organizer.data.model.PermissionsItemDto;
import com.wikitech.organizer.data.remote.api.PermissionsApi;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

public class PermissionsRemoteDataSource {
    private static final String TAG = "permission-remote-src";
    private final PermissionsApi api;

    public PermissionsRemoteDataSource(PermissionsApi api) {
        this.api = api;
    }

    public PermissionsItemDto getInfo() {
        try {
            Response<PermissionsItemDto> response = api.getInfo().execute();
            return response.body();
        } catch (IOException e) {
            Timber.tag(TAG).w(e, "Something went wrong");
            return null;
        }
    }
}
