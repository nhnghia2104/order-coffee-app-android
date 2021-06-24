package com.cogeek.tncoffee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cogeek.tncoffee.api.ProductApi;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.CategoryItem;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.cogeek.tncoffee.utils.SharedHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SharedHelper sharedHelper;
    private BottomNavigationView navView;
    private List<Category> mainData;
    private List<CategoryItem> categoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedHelper = SharedHelper.getInstance(this);
//        setupActionBar();
        setupBottomNavBar();
        getMainData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","stop");
    }

    private void setupBottomNavBar() {
        navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_menu)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.cartFragment :
                    case R.id.itemFragment:
                    case R.id.productReviewFragment:
                    case R.id.orderFragment:
                    case R.id.userOrderHistoryFragment:
                    case R.id.userOrderDetailFragment:
                    case R.id.orderTrackingFragment:
                    case R.id.userReviewProductFragment:
                        hideBottomNavView();
                        break;
                    default:
                        showBottomNavView();
                }
            }
        });
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void setupActionBar() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.main_action_bar);
        getSupportActionBar().setElevation(0);

    }

    private void getMainData() {
        mainData = new ArrayList<>();
        categoryItems = new ArrayList<>();

        ProductApi productApi = NetworkProvider.self().retrofit.create(ProductApi.class);
        Call<List<Category>> call = productApi.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<com.cogeek.tncoffee.models.Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    mainData.clear();
                    List<com.cogeek.tncoffee.models.Category> categories = response.body();
                    mainData.addAll(categories);

                    categoryItems.clear();
                    categoryItems.addAll(parseCategoryItem(mainData));
                }
            }

            @Override
            public void onFailure(Call<List<com.cogeek.tncoffee.models.Category>> call, Throwable t) {
                Log.e("erroor", "fail to load mainData" + t.getMessage());
            }
        });
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    private List<CategoryItem> parseCategoryItem(List<Category> categories) {
        List<CategoryItem> result = new ArrayList<>();
        result.add(new CategoryItem("Tất cả"));
        for (Category item: categories ) {
            result.add(new CategoryItem(item.getName()));
        }
        return result;
    }

    public List<Category> getProducts() {
        return mainData;
    }

    private void logout() {
        sharedHelper.logout();
        Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
        finish();
    }

    private void showBottomNavView() {
        navView.setVisibility(View.VISIBLE);
    }

    private void hideBottomNavView() {
        navView.setVisibility(View.GONE);
    }
}