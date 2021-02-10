package com.wikitech.organizer.domain.di;

import android.content.Context;

import com.wikitech.organizer.data.local.SharedPreferencesDataSource;
import com.wikitech.organizer.data.remote.api.PermissionsApi;

public class Container {
    private static Container instance;
    private final PermissionsApi permissionsApi;
    private SharedPreferencesDataSource sharedPreferencesDataSource;

    private Container(){
        permissionsApi = PermissionsApi.createApi();
    }

    public static Container getInstance(){
        if(instance == null){
            instance = new Container();
        }

        return instance;
    }

    public PermissionsApi getPermissionsApi() {
        return permissionsApi;
    }

    public SharedPreferencesDataSource getSharedPreferencesDataSource(Context context) {
        if(sharedPreferencesDataSource == null){
            sharedPreferencesDataSource = new SharedPreferencesDataSource(context);
        }

        return sharedPreferencesDataSource;
    }
}
