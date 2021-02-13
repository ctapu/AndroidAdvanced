package com.wikitech.organizer.presentation.people;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wikitech.organizer.domain.people.FetchPersonItemsUseCase;
import com.wikitech.organizer.domain.people.PersonItem;

import java.util.List;

import timber.log.Timber;

public class PersonsViewModel extends ViewModel {

    public static final String LOG_TAG = "People_tag";
    public ObservableArrayList<PersonItem> people = new ObservableArrayList<>();

    public PersonsViewModel(FetchPersonItemsUseCase fetchItemsUseCase){
        Timber.d("People displayed");

        LiveData<List<PersonItem>> liveItems = fetchItemsUseCase.execute();
        liveItems.observeForever(personItems -> people.addAll(personItems));
    }
}