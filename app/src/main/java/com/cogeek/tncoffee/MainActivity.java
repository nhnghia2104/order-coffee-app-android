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

import com.cogeek.tncoffee.utils.SharedHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedHelper sharedHelper;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                        hideBottomNavView();
                        break;
                    case R.id.itemFragment:
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