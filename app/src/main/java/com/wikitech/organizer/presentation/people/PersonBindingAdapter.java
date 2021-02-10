package com.wikitech.organizer.presentation.people;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wikitech.organizer.domain.people.PersonItem;

import java.util.List;

public class PersonBindingAdapter {

    @BindingAdapter("personItems")
    public static void setItems(RecyclerView recyclerView, List<PersonItem> items) {
        RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new PersonItemsAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        if (items != null) {
            ((PersonItemsAdapter) adapter).updateItems(items);
        }
    }
}
