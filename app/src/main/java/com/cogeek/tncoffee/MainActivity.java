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
import android.view.Window;

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
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedHelper = SharedHelper.getInstance(this);
//        setupActionBar();
        setupBottomNavBar();
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
                    case R.id.myReviewFragment:
                    case R.id.userAddressFragment:
                    case R.id.addressDetailFragment:
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