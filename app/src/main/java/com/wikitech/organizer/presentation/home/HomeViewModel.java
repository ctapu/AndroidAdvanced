package com.wikitech.organizer.presentation.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import timber.log.Timber;

public class HomeViewModel extends ViewModel {

    public static final String LOG_TAG = "home_tag";

    public MutableLiveData<Class> navigationLiveData = new MutableLiveData<>();

    public void onDayStatementButtonHit() {
        Timber.d("Day statement button was hit!");

        navigate();
    }

    public void onNightStatementButtonHit() {
        Timber.d("Day statement button was hit!");

        navigate();
    }

    public void navigate() {
        //navigationLiveData.setValue(HeavyActivity.class);
    }
}