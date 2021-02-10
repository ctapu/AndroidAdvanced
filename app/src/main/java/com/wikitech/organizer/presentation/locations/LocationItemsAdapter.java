package com.wikitech.organizer.presentation.locations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.wikitech.organizer.R;
import com.wikitech.organizer.databinding.LocationItemBinding;
import com.wikitech.organizer.domain.location.LocationItem;

import java.util.ArrayList;
import java.util.List;

public class LocationItemsAdapter extends Adapter<LocationItemsAdapter.LocationItemsViewHolder> {
    private final List<LocationItem> items;

    public LocationItemsAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public LocationItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.location_item, parent, false);

        return new LocationItemsViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LocationItemsViewHolder holder, int position) {
        LocationItem item = items.get(position);
        holder.bind(item);
    }

    public void updateItems(List<LocationItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    static class LocationItemsViewHolder extends RecyclerView.ViewHolder {
        private final LocationItemBinding binding;

        public LocationItemsViewHolder(@NonNull LocationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(LocationItem model) {
            binding.setModel(model);
        }
    }
}
