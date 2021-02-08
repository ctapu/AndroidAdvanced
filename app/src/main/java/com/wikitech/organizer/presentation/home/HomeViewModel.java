package com.wikitech.organizer.presentation.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public static final String LOG_TAG = "home_tag";

    public MutableLiveData<Class> navigationLiveData = new MutableLiveData<>();

    public void onDayStatementButtonHit() {
        Log.d(LOG_TAG, "Day statement button was hit!");

        navigate();
    }

    public void onNightStatementButtonHit() {
        Log.d(LOG_TAG, "Day statement button was hit!");

        navigate();
    }

    public void navigate() {
        //navigationLiveData.setValue(HeavyActivity.class);
    }
}