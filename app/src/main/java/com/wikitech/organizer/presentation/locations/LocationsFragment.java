package com.wikitech.organizer.presentation.locations;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wikitech.organizer.R;
import com.wikitech.organizer.data.local.LocationInMemoryDataSource;
import com.wikitech.organizer.data.remote.LocationRemoteDataSource;
import com.wikitech.organizer.data.remote.api.LocationApi;
import com.wikitech.organizer.databinding.FragmentLocationsBinding;
import com.wikitech.organizer.domain.location.FetchLocationItemsUseCase;
import com.wikitech.organizer.domain.location.LocationItemsMediator;
import com.wikitech.organizer.domain.location.LocationItemsRepository;

public class LocationsFragment extends Fragment {

    private static final String LOG_TAG = "Locations";
    private LocationViewModel locationViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG,"LocationsFragment created");

        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                FetchLocationItemsUseCase useCase = createUseCase();
                return (T) new LocationViewModel(useCase);
            }
        };

        locationViewModel = new ViewModelProvider(this, factory).get(LocationViewModel.class);

        FragmentLocationsBinding binding =
                DataBindingUtil.setContentView(getActivity(), R.layout.fragment_locations);
        binding.setLocationsViewModel(locationViewModel);
    }

      private FetchLocationItemsUseCase createUseCase() {
        LocationItemsRepository localRepository = new LocationInMemoryDataSource();
        LocationItemsRepository remoteRepository = new LocationRemoteDataSource(LocationApi.createApi());

        LocationItemsMediator mediator = new LocationItemsMediator(localRepository, remoteRepository);

        return new FetchLocationItemsUseCase(mediator);
    }
}