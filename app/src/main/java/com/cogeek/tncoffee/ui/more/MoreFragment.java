package com.cogeek.tncoffee.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button btnUserInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
//        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//        }
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.vLogout).setOnClickListener(v -> {
            logout();
        });

        btnUserInfo = view.findViewById(vInfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserInfo();
            }
        });
    }

    private void openUserInfo() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out,R.anim.slide_in_left, R.anim.fade_out);
        transaction.addToBackStack(null);
        transaction.add(R.id.vInfo, UserInfoFragment.class, null).commit();
    }

    //jump to user info layout
    private void logout() {
        SharedHelper.getInstance(getActivity()).logout();
        Intent intent = new Intent(getActivity(), LaunchActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
        getActivity().finish();
    }
}
