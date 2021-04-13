package com.cogeek.tncoffee.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.MainActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Store;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    private ListView listView;
    private StoreAdapter storeAdapter;
    private ArrayList<Store> arrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_store, container, false);
//        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // fake data arraylist
        arrayList = new ArrayList<>();
        arrayList.add(new Store("Z! Cafe", "301 cư xá đường", 1.0, "https://media-cdn.tripadvisor.com/media/photo-s/05/8f/89/66/z-cafe.jpg"));

        // list view
        listView = view.findViewById(R.id.listViewStore);

        // init Store Adapter
        storeAdapter = new StoreAdapter(getActivity(),R.layout.store, arrayList);

        // set adapter
        listView.setAdapter(storeAdapter);
    }
}
