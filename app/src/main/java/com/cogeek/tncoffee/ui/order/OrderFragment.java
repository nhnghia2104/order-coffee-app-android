package com.cogeek.tncoffee.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cogeek.tncoffee.MenuItemActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Category;

import java.util.ArrayList;

public class OrderFragment extends Fragment {
    ListView listViewCategory;
    CategoryAdapter adapterCategory;
    ArrayList<Category> arrayListCategory;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewCategory = view.findViewById(R.id.lvCategory);
        fakeData();
        addEvent();
        adapterCategory = new CategoryAdapter(getActivity(),R.layout.category,arrayListCategory);
        listViewCategory.setAdapter(adapterCategory);
    }

    private void addEvent() {
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavHostFragment.findNavController(OrderFragment.this).navigate(R.id.action_Order_to_MenuItem);
                startActivity(new Intent(getActivity(), MenuItemActivity.class));
            }
        });
    }

    private void fakeData() {
        arrayListCategory = new ArrayList<Category>();
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));
        arrayListCategory.add(new Category("Món phải thử","8 món","https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/cogeek.png?alt=media&token=dd115330-4cf5-44f0-808d-766b1d886fd6"));

    }
}
