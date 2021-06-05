package com.cogeek.tncoffee.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cogeek.tncoffee.LaunchActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.models_old.Store;
import com.cogeek.tncoffee.ui.store.StoreAdapter;
import com.cogeek.tncoffee.utils.SharedHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoreFragment extends Fragment {

    private ListView listView;
    private StoreAdapter storeAdapter;
    private ArrayList<Store> arrayList;
    private ImageView imgUserInfo;
    private TextView txtUserName;
    private ConstraintLayout layoutOrder;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
//        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//        }


        return root;
    }

    private void openUserInfo() {
        NavHostFragment.findNavController(MoreFragment.this).navigate(R.id.action_navigation_more_to_userInfoFragment);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.layout_user_logout).setOnClickListener(v -> {
           logout();
       });
        imgUserInfo = view.findViewById(R.id.img_user_avt);
        txtUserName = view.findViewById(R.id.txt_user_name);
        layoutOrder = view.findViewById(R.id.layout_user_order);

        user = SharedHelper.getInstance(getActivity()).getUserProfile();

        txtUserName.setText(user.getFullName());
        Picasso.get().load(user.getAvatar()).placeholder(R.drawable.ic_zcafe_hint).centerCrop().fit().into(imgUserInfo);
        layoutOrder.setOnClickListener( v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", user.getUid());
            NavHostFragment.findNavController(MoreFragment.this).navigate(R.id.action_navigation_more_to_userOrderHistoryFragment,bundle);
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
