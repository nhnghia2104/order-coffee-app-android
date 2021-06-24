package com.cogeek.tncoffee.ui.personal;

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

public class PersonalFragment extends Fragment {

    private ListView listView;
    private StoreAdapter storeAdapter;
    private ArrayList<Store> arrayList;
    private ImageView imgUserInfo;
    private TextView txtUserName;
    private ConstraintLayout layoutOrder, layoutProductHistory, layoutMyReview, layoutReviewProduct;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personal, container, false);
        return root;
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
        layoutProductHistory = view.findViewById(R.id.layout_user_product_history);
        layoutReviewProduct = view.findViewById(R.id.layout_review_product);

        user = SharedHelper.getInstance(getActivity()).getUserProfile();

        txtUserName.setText(user.getFullName());
        Picasso.get()
                .load(user.getAvatar())
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .centerCrop()
                .fit()
                .into(imgUserInfo);
        layoutOrder.setOnClickListener(onClickLayoutOrder);
        imgUserInfo.setOnClickListener(onClickUserAvatar);
        layoutProductHistory.setOnClickListener(onClickProductBought);
        layoutReviewProduct.setOnClickListener(onClickReviewProduct);
    }


    private void logout() {
        SharedHelper.getInstance(getActivity()).logout();
        Intent intent = new Intent(getActivity(), LaunchActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
        getActivity().finish();
    }

    View.OnClickListener onClickLayoutOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("id", user.getUid());
            NavHostFragment.findNavController(PersonalFragment.this).navigate(R.id.action_navigation_more_to_userOrderHistoryFragment,bundle);
        }
    };

    View.OnClickListener onClickUserAvatar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openUserInfo();
        }
    };

    View.OnClickListener onClickProductBought = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("id", user.getUid());
            NavHostFragment.findNavController(PersonalFragment.this).navigate(R.id.action_navigation_more_to_userProductFragment,bundle);
        }
    };

    View.OnClickListener onClickMyReview = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("id", user.getUid());
            NavHostFragment.findNavController(PersonalFragment.this).navigate(R.id.action_navigation_more_to_userProductFragment,bundle);
        }
    };

    View.OnClickListener onClickReviewProduct = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("id", user.getUid());
            NavHostFragment.findNavController(PersonalFragment.this).navigate(R.id.action_navigation_more_to_userReviewOverviewFragment,bundle);
        }
    };


    private void openUserInfo() {
        NavHostFragment.findNavController(PersonalFragment.this).navigate(R.id.action_navigation_more_to_userInfoFragment);
    }
}
