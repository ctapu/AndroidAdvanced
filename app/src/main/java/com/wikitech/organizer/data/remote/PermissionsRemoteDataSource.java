package com.wikitech.organizer.data.remote;

import com.wikitech.organizer.data.model.PermissionsItemDto;
import com.wikitech.organizer.data.remote.api.PermissionsApi;
import com.wikitech.organizer.domain.di.Container;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

public class PermissionsRemoteDataSource {
    private static final String TAG = "permission-remote-src";
    private final PermissionsApi api;

    public PermissionsRemoteDataSource() {
        api = Container.getInstance().getPermissionsApi();
    }

    public PermissionsItemDto getInfo() {
        try {
            Response<List<PermissionsItemDto>> response = api.getInfo().execute();
            List<PermissionsItemDto> body = response.body();
            if (body.size() > 0) {
                return body.get(0);
            } else {
                Timber.e("Permissions list is empty! when trying to get permisssions");
                return null;
            }
        } catch (IOException e) {
            Timber.tag(TAG).w(e, "Something went wrong");
            return null;
        }
    }
}
