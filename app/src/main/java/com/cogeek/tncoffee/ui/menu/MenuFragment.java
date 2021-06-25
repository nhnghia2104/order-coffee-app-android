    package com.cogeek.tncoffee.ui.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cogeek.tncoffee.MainActivity;
import com.cogeek.tncoffee.api.ProductApi;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.SearchItemActivity;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.CategoryItem;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.cart.CartBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.menu.category.CategoryAdapter;
import com.cogeek.tncoffee.ui.menu.item.ItemBottomSheetDialogFragment;
import com.cogeek.tncoffee.ui.menu.item.MainItemAdapter;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

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
//    private RecyclerView.LayoutManager layoutManager;
    private ItemViewModel itemViewModel;
    private List<com.cogeek.tncoffee.models.Category> productList;
    private List<com.cogeek.tncoffee.models.Category> filteredList;
    private TextView stickyView;
    private View heroImageView;
    private View stickyViewSpacer;
    private View view;
    private View header;
    private SearchView searchView;
    private AppCompatImageButton btnOpenCart;
    private View layoutCategory;
    private TabLayout tabsCategory;

    private Spinner categoriesSpinner;
    private List<Category> categoryItems;
    private CategoryAdapter categoryAdapter;

    private BlurView blurView;
    RecyclerView.SmoothScroller smoothScroller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
//        layoutManager = new LinearLayoutManager(getContext());
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
//        getActivity().setProgressBarIndeterminateVisibility(true);

        productList = new ArrayList<>();
        filteredList = new ArrayList<>();
        categoryItems = new ArrayList<>();

        btnOpenCart = view.findViewById(R.id.btn_open_cart);
        recyclerViewListItem = view.findViewById(R.id.recycler_view_item);
        mainItemAdapter = new MainItemAdapter(filteredList);
        mainItemAdapter.setOnItemListener(itemListener);
        recyclerViewListItem.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewListItem.setAdapter(mainItemAdapter);
        tabsCategory = view.findViewById(R.id.tabs_category);

        addEvent();
        setupMainContent();
//        categoriesSpinner = view.findViewById(R.id.spinner_category);
//        setupCategorySpinner();


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
        Call<List<com.cogeek.tncoffee.models.Category>> callProduct = productApi.getProducts();

        callProduct.enqueue(new Callback<List<com.cogeek.tncoffee.models.Category>>() {
            @Override
            public void onResponse(Call<List<com.cogeek.tncoffee.models.Category>> call, Response<List<com.cogeek.tncoffee.models.Category>> response) {
                if (response.isSuccessful()) {
                    productList.clear();
                    filteredList.clear();
                    List<com.cogeek.tncoffee.models.Category> categories = response.body();
                    productList.addAll(categories);
                    filteredList.addAll(categories);
                    mainItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<com.cogeek.tncoffee.models.Category>> call, Throwable t) {

            }
        });

        Call<List<Category>> callCategory = productApi.getCategories();
        callCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    categoryItems.clear();
                    categoryItems.add(new Category("Tất cả"));
                    List<Category> responseData = response.body();
                    categoryItems.addAll(responseData);
                    setupTabsCategory();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
//
//        productList = new ArrayList<>();
//        filteredList.addAll(productList);
//        mainItemAdapter.notifyDataSetChanged();
    }

    MainItemAdapter.OnItemListener itemListener = new MainItemAdapter.OnItemListener() {
        @Override
        public void onItemClick(int section, int row) {
//            Log.i("click item at", "section: " + section + ", row: " + row);
//            ItemBottomSheetDialogFragment bottomSheetDialog = new ItemBottomSheetDialogFragment();
//            bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Detail Item");
            Product item = filteredList.get(section).getItems().get(row);
            itemViewModel.selectItem(item);
            NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.action_menu_open_item);
        }
    };

    private void addEvent() {

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
//                CartBottomSheetDialogFragment cartBottomSheetDialogFragment = new CartBottomSheetDialogFragment();
//                cartBottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), "Detail Item");
                NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.action_navigation_menu_to_cartFragment);
            }
        });
    }

    private void setupTabsCategory() {
        tabsCategory.removeAllTabs();
        for(Category item : categoryItems) {
            tabsCategory.addTab(tabsCategory.newTab().setText(item.getName()));
        }

        tabsCategory.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setFilteredList(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setFilteredList(int position) {
        if (position == 0) {
            filteredList.clear();
            filteredList.addAll(productList);
            mainItemAdapter.notifyDataSetChanged();
        } else {
            filteredList.clear();
            filteredList.addAll(productList.subList(position - 1, position));
            mainItemAdapter.notifyDataSetChanged();
        }
    }
}

