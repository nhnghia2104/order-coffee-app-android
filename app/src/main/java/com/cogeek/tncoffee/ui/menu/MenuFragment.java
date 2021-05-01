package com.cogeek.tncoffee.ui.menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.SearchItemActivity;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Item;
import com.cogeek.tncoffee.ui.cart.CartBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.item.ItemBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.item.MainItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private RecyclerView recyclerViewListItem;
    private MainItemAdapter mainItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Category> categoryList;
    private TextView stickyView;
    private View heroImageView;
    private View stickyViewSpacer;
    private View view;
    private View header;
    private SearchView searchView;
    private Button btnOpenCart;

    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        layoutManager = new LinearLayoutManager(getContext());
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
        /* Initialise views */
//        listView = view.findViewById(R.id.listViewItem);
//        stickyView = view.findViewById(R.id.stickyView);
//        heroImageView = view.findViewById(R.id.heroImageView);
//        searchView = header.findViewById(R.id.searchView);

        /* Init list header layout */
//        header = getLayoutInflater().inflate(R.layout.header_category_listview, null, false);
//        stickyViewSpacer = header.findViewById(R.id.stickyViewPlaceholder);

        /* Add list view header */
//        listView.addHeaderView(header);

        /* create and set adapter */
//        itemAdapter = new ItemAdapter(getActivity(), R.layout.item, listItems);
//        listView.setAdapter(itemAdapter);
        btnOpenCart = view.findViewById(R.id.btnOpenCart);
        recyclerViewListItem = view.findViewById(R.id.recycler_view_item);
        mainItemAdapter = new MainItemAdapter(categoryList);
        mainItemAdapter.setOnItemListener(new MainItemAdapter.OnItemListener() {
            @Override
            public void onItemClick(int section, int row) {
                Log.i("click item at", "section: " + section + ", row: " + row);
                ItemBottomSheetDialogFragment bottomSheetDialog = new ItemBottomSheetDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", categoryList.get(section).getItems().get(row).getName());
                bundle.putString("description", categoryList.get(section).getItems().get(row).getDescription());
                bundle.putInt("price", categoryList.get(section).getItems().get(row).getPrice());
                bundle.putString("imageUrl", categoryList.get(section).getItems().get(row).getImageUrl());
                bottomSheetDialog.setArguments(bundle);
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Detail Item");
            }
        });
        recyclerViewListItem.setLayoutManager(layoutManager);
        recyclerViewListItem.setAdapter(mainItemAdapter);
    }


    private void addEvent() {
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.action_Order_to_MenuItem);
                ItemBottomSheetDialogFragment bottomSheetDialog = new ItemBottomSheetDialogFragment();
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Detail Item");
            }
        });*/

//        recyclerViewListItem.setOnClickListener(new );

//        header.findViewById(R.id.vCurrentOrder).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("click", "ngon click Curretnt Order rồi");
//            }
//        });

//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                /* Check if the first item is already reached to top.*/
//                if (listView.getFirstVisiblePosition() == 0) {
//                    View firstChild = listView.getChildAt(0);
//                    int topY = 0;
//                    if (firstChild != null) {
//                        topY = firstChild.getTop();
//                    }
//
//                    if (stickyView != null) {
//                        int heroTopY = stickyViewSpacer.getTop();
//                        stickyView.setY(Math.max(0, heroTopY + topY));
//                    }
//
//                    /* Set the image to scroll half of the amount that of ListView */
//                    if (heroImageView != null) {
//                        heroImageView.setY(topY * 0.5f);
//                    }
//                }
//            }
//        });

        if (searchView != null) {
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

        btnOpenCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartBottomSheetDialogFragment cartBottomSheetDialogFragment = new CartBottomSheetDialogFragment();
                cartBottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), "Detail Item");
            }
        });
    }

    private void fakeData() {
        categoryList = new ArrayList<>();

        List<Item> list1 = new ArrayList<>();
        list1.add(new Item("Trà Sữa êi", "em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm em non lắm", 10, "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"));
        list1.add(new Item("Trà Sữa êi", "em non lắm", 10, "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"));
        list1.add(new Item("Trà Sữa êi", "em non lắm", 10, "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"));

        List<Item> list2 = new ArrayList<>();
        list2.add(new Item("Trà Sữa êi", "em non lắm", 10, "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"));
        list2.add(new Item("Trà Sữa êi", "em non lắm", 10, "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"));

        categoryList.add(new Category("Trà sữa", list1));
        categoryList.add(new Category("Cà phê", list2));

    }

    private ValueEventListener categoryValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}