package com.cogeek.tncoffee.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.MenuItemActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.SearchItemActivity;
import com.cogeek.tncoffee.models.Category;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class OrderFragment extends Fragment {
    private ListView listViewCategory;
    private CategoryAdapter adapterCategory;
    private ArrayList<Category> arrayListCategory;
    private View view;
    private View header;
    private SearchView searchView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

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

    private void addControl() {
        listViewCategory = view.findViewById(R.id.lvCategory);
        adapterCategory = new CategoryAdapter(getActivity(),R.layout.category,arrayListCategory);
        this.header = getLayoutInflater().inflate(R.layout.header_category_listview, null, false);
        listViewCategory.addHeaderView(header);
        listViewCategory.setAdapter(adapterCategory);

        this.searchView = header.findViewById(R.id.searchView);

    }


    private void addEvent() {
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavHostFragment.findNavController(OrderFragment.this).navigate(R.id.action_Order_to_MenuItem);
                startActivity(new Intent(getActivity(), MenuItemActivity.class));
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                    Log.i("index", String.valueOf(position));
            }
        });
        header.findViewById(R.id.vCurrentOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click", "ngon click Curretnt Order rồi");
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
                startActivity(new Intent(getActivity(), SearchItemActivity.class));
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchItemActivity.class));
            }
        });
    }

    private void fakeData() {
        arrayListCategory = new ArrayList<Category>();
        arrayListCategory.add(new Category("Món phải thử","1 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Cà phê","2 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Trà trái cây","3 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Các món trà","4 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Juice & Smoothie","5 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
    }
}
