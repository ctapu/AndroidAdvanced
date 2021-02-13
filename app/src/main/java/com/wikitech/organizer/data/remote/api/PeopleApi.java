package com.wikitech.organizer.data.remote.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wikitech.organizer.data.model.PersonItemDto;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PeopleApi {
    String BASE_URL = "https://console.firebase.google.com/u/0/project/organizer-51dc9/firestore/data~2Fpeople";

    @GET("people.json")
    Call<List<PersonItemDto>> getItems();

    static PeopleApi createApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PeopleApi.class);
    }
}
