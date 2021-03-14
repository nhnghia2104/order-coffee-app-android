package com.cogeek.tncoffee.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Item;

import java.util.ArrayList;

public class ItemFragment extends Fragment {
    private ListView listView;
    private ItemAdapter adapter;
    private ArrayList<Item> arrayList;
    private View view;
    private View header;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        fakeData();
        addControl();
        addEvent();
    }

    private void addEvent() {
    }

    private void addControl() {
        listView = view.findViewById(R.id.lvItem);
        adapter = new ItemAdapter(getActivity(),R.layout.item, arrayList);
        this.header = getLayoutInflater().inflate(R.layout.header_item_listview, null, false);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);
    }

    private void fakeData() {
        arrayList = new ArrayList<Item>();
        arrayList.add(new Item("Ngon","non",50,"rac"));
        arrayList.add(new Item("Ngon","non",50,"rac"));
    }
}
