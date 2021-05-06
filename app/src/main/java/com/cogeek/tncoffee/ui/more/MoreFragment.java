package com.cogeek.tncoffee.ui.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cogeek.tncoffee.LaunchActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Store;
import com.cogeek.tncoffee.ui.store.StoreAdapter;
import com.cogeek.tncoffee.ui.userinfo.UserInfoFragment;
import com.cogeek.tncoffee.utils.SharedHelper;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;

import kotlin.collections.IntIterator;

import static com.cogeek.tncoffee.R.id.vInfo;

public class MoreFragment extends Fragment {

    private ListView listView;
    private StoreAdapter storeAdapter;
    private ArrayList<Store> arrayList;
    private ImageView imgvUserInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
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
        Fragment fragment = new UserInfoFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.more_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
