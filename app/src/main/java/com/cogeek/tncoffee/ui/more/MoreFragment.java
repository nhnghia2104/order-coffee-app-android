package com.cogeek.tncoffee.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cogeek.tncoffee.LaunchActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models_old.Store;
import com.cogeek.tncoffee.ui.store.StoreAdapter;
import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.ArrayList;

public class MoreFragment extends Fragment {

    private ListView listView;
    private StoreAdapter storeAdapter;
    private ArrayList<Store> arrayList;
    private ImageView imgvUserInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
//        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//        }

        imgvUserInfo = root.findViewById(R.id.imgvUserInfo);
        imgvUserInfo.setOnClickListener(new View.OnClickListener() {
            //jump to user info
            @Override
            public void onClick(View v) {
                openUserInfo();
            }
        });

        return root;
    }

    private void openUserInfo() {
        NavHostFragment.findNavController(MoreFragment.this).navigate(R.id.action_navigation_more_to_userInfoFragment);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.vLogout).setOnClickListener(v -> {
            logout();
        });
    }


    private void logout() {
        SharedHelper.getInstance(getActivity()).logout();
        Intent intent = new Intent(getActivity(), LaunchActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
        getActivity().finish();
    }
}
