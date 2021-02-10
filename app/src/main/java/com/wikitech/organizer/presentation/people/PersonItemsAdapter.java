package com.wikitech.organizer.presentation.people;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.wikitech.organizer.R;
import com.wikitech.organizer.databinding.PersonItemBinding;
import com.wikitech.organizer.domain.people.PersonItem;

import java.util.ArrayList;
import java.util.List;

public class PersonItemsAdapter extends Adapter<PersonItemsAdapter.PeopleItemsViewHolder> {
    private final List<PersonItem> items;

    public PersonItemsAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public PeopleItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.person_item, parent, false);

        return new PeopleItemsViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleItemsViewHolder holder, int position) {
        PersonItem item = items.get(position);
        holder.bind(item);
    }

    public void updateItems(List<PersonItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    static class PeopleItemsViewHolder extends RecyclerView.ViewHolder {
        private final PersonItemBinding binding;

        public PeopleItemsViewHolder(@NonNull PersonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PersonItem model) {
            binding.setModel(model);
        }
    }
}
