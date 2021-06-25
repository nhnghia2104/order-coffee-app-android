package com.cogeek.tncoffee.ui.userproduct;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.ProductApi;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProductHistoryFragment extends Fragment {

    List<Product> productList;
    RecyclerView recyclerView;
    UserProductAdapter adapter;
    View layoutError;
    private static final String ARG_ID = "id";
    private String mID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mID = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_product_bought);
        layoutError = view.findViewById(R.id.layout_error);
        ProductApi productApi = NetworkProvider.self().retrofit.create(ProductApi.class);
        Call<List<Product>> call = productApi.getListCustomerBought(mID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productList = new ArrayList<>();
        adapter = new UserProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> list = response.body();
                    productList.clear();
                    productList.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.btn_close).setOnClickListener(v -> {
            NavHostFragment.findNavController(UserProductHistoryFragment.this).popBackStack();
        });
    }
}