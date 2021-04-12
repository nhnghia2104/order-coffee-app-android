package com.cogeek.tncoffee.ui.menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.SearchItemActivity;
import com.cogeek.tncoffee.models.Item;
import com.cogeek.tncoffee.ui.item.ItemAdapter;
import com.cogeek.tncoffee.ui.item.ItemBottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private ListView listView;
    private ItemAdapter itemAdapter;
    private ArrayList<Item> arrayList;
    private TextView stickyView;
    private View heroImageView;
    private View stickyViewSpacer;
    private View view;
    private View header;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

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
        listView = view.findViewById(R.id.listViewItem);
        stickyView = view.findViewById(R.id.stickyView);
        heroImageView = view.findViewById(R.id.heroImageView);
//        searchView = header.findViewById(R.id.searchView);

        /* Init list header layout */
        header = getLayoutInflater().inflate(R.layout.header_category_listview, null, false);
//        stickyViewSpacer = header.findViewById(R.id.stickyViewPlaceholder);

        /* Add list view header */
        listView.addHeaderView(header);

        /* create and set adapter */
        itemAdapter = new ItemAdapter(getActivity(), R.layout.item, arrayList);
        listView.setAdapter(itemAdapter);

    }


    private void addEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.action_Order_to_MenuItem);
                ItemBottomSheetDialogFragment bottomSheetDialog = new ItemBottomSheetDialogFragment();
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Detail Item");
            }
        });
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
    }

    private void fakeData() {
        arrayList = new ArrayList<Item>();
        arrayList.add(new Item("Hộp quà Tết 2021", "Mang Tết về nhà là đây!!!!", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
        arrayList.add(new Item("Ngon", "non", 50, "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2"));
    }
}