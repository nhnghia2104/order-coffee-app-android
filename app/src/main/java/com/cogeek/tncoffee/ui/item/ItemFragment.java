package com.cogeek.tncoffee.ui.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Item;
import com.cogeek.tncoffee.ui.home.NotificationBottomSheetDialogFragment;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemBottomSheetDialogFragment bottomSheetDialog = new ItemBottomSheetDialogFragment();
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(),"Detail Item");
            }
        });
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
        arrayList.add(new Item("Ngon","non",50,"https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon","non",50,"https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
    }
}
