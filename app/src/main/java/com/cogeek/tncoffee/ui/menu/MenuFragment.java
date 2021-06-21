    package com.cogeek.tncoffee.ui.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cogeek.tncoffee.api.ProductApi;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.SearchItemActivity;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.CategoryItem;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.cart.CartBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.menu.category.CategoryAdapter;
import com.cogeek.tncoffee.ui.menu.category.CategoryBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.menu.item.ItemBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.menu.item.MainItemAdapter;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    private RecyclerView recyclerViewListItem;
    private MainItemAdapter mainItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ItemViewModel itemViewModel;
    private List<com.cogeek.tncoffee.models.Category> categoryList;
    private TextView stickyView;
    private View heroImageView;
    private View stickyViewSpacer;
    private View view;
    private View header;
    private SearchView searchView;
    private Button btnOpenCart;
    private View layoutCategory;

    private Spinner categoriesSpinner;
    private List<CategoryItem> categoryItems;
    private CategoryAdapter categoryAdapter;

    private BlurView blurView;
    RecyclerView.SmoothScroller smoothScroller;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        layoutManager = new LinearLayoutManager(getContext());
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        smoothScroller = new LinearSmoothScroller(requireContext()) {
            @Override protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 15f / displayMetrics.densityDpi;
            }
        };
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryList = new ArrayList<>();

        btnOpenCart = view.findViewById(R.id.btnOpenCart);
        recyclerViewListItem = view.findViewById(R.id.recycler_view_item);
        mainItemAdapter = new MainItemAdapter(categoryList);
        mainItemAdapter.setOnItemListener(itemListener);
        recyclerViewListItem.setLayoutManager(layoutManager);
        recyclerViewListItem.setAdapter(mainItemAdapter);

        addEvent();
        setupMainContent();
        categoriesSpinner = view.findViewById(R.id.spinner_category);
        setupCategorySpinner();
        blurView = view.findViewById(R.id.blur_layout);
        blurBackground();
    }
    private void blurBackground() {
        float radius = 20f;

        View decorView = getActivity().getWindow().getDecorView();
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(getContext()))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true); // Or false if it's in a scrolling container or might be animated
    }

    private void setupMainContent() {
        ProductApi productApi = NetworkProvider.self().retrofit.create(ProductApi.class);
        Call<List<com.cogeek.tncoffee.models.Category>> call = productApi.getCategories();

        call.enqueue(new Callback<List<com.cogeek.tncoffee.models.Category>>() {
            @Override
            public void onResponse(Call<List<com.cogeek.tncoffee.models.Category>> call, Response<List<com.cogeek.tncoffee.models.Category>> response) {
                if (response.isSuccessful()) {
                    categoryList.clear();
                    List<com.cogeek.tncoffee.models.Category> categories = response.body();
                    categoryList.addAll(categories);
                    mainItemAdapter.notifyDataSetChanged();

                    /* setup spinner */
                    categoryItems.clear();
                    categoryItems.addAll(parseCategoryItem(categories));
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<com.cogeek.tncoffee.models.Category>> call, Throwable t) {

            }
        });
    }

    MainItemAdapter.OnItemListener itemListener = new MainItemAdapter.OnItemListener() {
        @Override
        public void onItemClick(int section, int row) {
            Log.i("click item at", "section: " + section + ", row: " + row);
            ItemBottomSheetDialogFragment bottomSheetDialog = new ItemBottomSheetDialogFragment();
            Product item = categoryList.get(section).getItems().get(row);
            itemViewModel.selectItem(item);
            bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Detail Item");
        }
    };

    private void addEvent() {

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

    private void setupCategorySpinner() {
        categoryItems = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), R.layout.category_spinner_row, categoryItems);
        categoriesSpinner.setAdapter(categoryAdapter);

        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                recyclerViewListItem.scrollToPosition(position);
                smoothScroller.setTargetPosition(position);
//                layoutManager.startSmoothScroll(smoothScroller);
                recyclerViewListItem.getLayoutManager().startSmoothScroll(smoothScroller);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<CategoryItem> parseCategoryItem(List<Category> categories) {
        List<CategoryItem> result = new ArrayList<>();
        for (Category item: categories ) {
            result.add(new CategoryItem(item.getName(), findIcon(item.getName())));
        }
        return result;
    }

    private int findIcon(String name) {
        return 0;
    }

}

