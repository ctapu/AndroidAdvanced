package com.wikitech.organizer.data.remote.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wikitech.organizer.data.model.LocationItemDto;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface LocationApi {
    String BASE_URL = "TODO";

    @GET("items.json")
    Call<List<LocationItemDto>> getItems();

    static LocationApi createApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LocationApi.class);
    }
}
