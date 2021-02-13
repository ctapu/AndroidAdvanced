package com.wikitech.organizer.presentation.people;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wikitech.organizer.R;
import com.wikitech.organizer.data.local.PersonInMemoryDataSource;
import com.wikitech.organizer.data.remote.PersonRemoteDataSource;
import com.wikitech.organizer.data.remote.api.PeopleApi;
import com.wikitech.organizer.databinding.FragmentPeopleBinding;
import com.wikitech.organizer.domain.people.FetchPersonItemsUseCase;
import com.wikitech.organizer.domain.people.PersonItemsMediator;
import com.wikitech.organizer.domain.people.PersonItemsRepository;

import timber.log.Timber;

public class PersonsFragment extends Fragment {

    private static final String LOG_TAG = "People";
    private PersonsViewModel personsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("PeopleFragment created");

        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                FetchPersonItemsUseCase useCase = createUseCase();
                return (T) new PersonsViewModel(useCase);
            }
        };

        personsViewModel = new ViewModelProvider(this, factory).get(PersonsViewModel.class);

        FragmentPeopleBinding binding =
                DataBindingUtil.setContentView(getActivity(), R.layout.fragment_people);
        binding.setPeopleViewModel(personsViewModel);
    }

    private FetchPersonItemsUseCase createUseCase() {
        PersonItemsRepository localRepository = new PersonInMemoryDataSource();
        PersonItemsRepository remoteRepository = new PersonRemoteDataSource(PeopleApi.createApi());

        PersonItemsMediator mediator = new PersonItemsMediator(localRepository, remoteRepository);

        return new FetchPersonItemsUseCase(mediator);
    }
}