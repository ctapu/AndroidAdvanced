package com.wikitech.organizer.presentation.locations;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.databinding.BindingAdapter;

import com.wikitech.organizer.domain.LocationItem;

import java.util.List;

public class LocationsBindingAdapter {

    @BindingAdapter("locationItems")
    public static void setItems(RecyclerView recyclerView, List<LocationItem> items) {
        RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new LocationItemsAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        if (items != null) {
            ((LocationItemsAdapter) adapter).updateItems(items);
        }
    }
}
