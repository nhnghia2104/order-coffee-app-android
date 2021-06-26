package com.cogeek.tncoffee.ui.useraddress;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Address;
import com.cogeek.tncoffee.models.UserAddress;
import com.cogeek.tncoffee.ui.menu.ItemViewModel;
import com.cogeek.tncoffee.ui.userreview.UserReviewProductFragment;
import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.List;

public class UserAddressFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAddress userAddress;
    private UserAddressAdapter addressAdapter;
    private AddressViewModel addressViewModel;
    private boolean isEditing = true;

    public UserAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { // is editing address
//            mID = getArguments().getString(ARG_ID);
        }
        else { // is selecting address
            isEditing = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userAddress = SharedHelper.getInstance(getContext()).getUserProfile().getUserAddress();
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        return inflater.inflate(R.layout.fragment_user_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_address);
        userAddress = SharedHelper.getInstance(getContext()).getUserProfile().getUserAddress();
        if (userAddress == null) {
            userAddress = new UserAddress();
        }
        addressAdapter = new UserAddressAdapter(userAddress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(addressAdapter);

        addressAdapter.setOnChildListener(new UserAddressAdapter.OnChildListener() {
            @Override
            public void onChildClick(int position) {
                if (isEditing) {
                    addressViewModel.setIndex(position);
                    NavHostFragment.findNavController(UserAddressFragment.this).navigate(R.id.action_userAddressFragment_to_addressDetailFragment);
                }
                else {
                    addressViewModel.setCartSelected(position);
                    NavHostFragment.findNavController(UserAddressFragment.this).popBackStack();
                }
            }
        });
        view.findViewById(R.id.btn_close).setOnClickListener(v -> {
            NavHostFragment.findNavController(UserAddressFragment.this).popBackStack();
        });
        view.findViewById(R.id.btn_add).setOnClickListener(v -> {
            addressViewModel.setIndex(-1);
            NavHostFragment.findNavController(UserAddressFragment.this).navigate(R.id.action_userAddressFragment_to_addressDetailFragment);
        });
    }
}