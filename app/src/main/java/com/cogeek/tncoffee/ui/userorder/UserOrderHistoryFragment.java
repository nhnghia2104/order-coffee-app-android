package com.cogeek.tncoffee.ui.userorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.OrderApi;
import com.cogeek.tncoffee.api.UserApi;
import com.cogeek.tncoffee.models.OrderHistoryOverview;
import com.cogeek.tncoffee.ui.more.MoreFragment;
import com.cogeek.tncoffee.utils.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrderHistoryFragment extends Fragment {

    private static final String ARG_ID = "id";
    private String mID;
    private RecyclerView recyclerView;
    private UserOrderOverviewAdapter adapter;
    private List<OrderHistoryOverview> listOrder;
    private ProgressBar progressBar;

    public UserOrderHistoryFragment() {
        // Required empty public constructor
    }

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
        return inflater.inflate(R.layout.fragment_user_order_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rv_order_history);
        listOrder = new ArrayList<>();
        adapter = new UserOrderOverviewAdapter(listOrder);
        adapter.setOnChildListener(this.onChildListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        OrderApi orderApi = NetworkProvider.self().retrofit.create(OrderApi.class);
        Call<List<OrderHistoryOverview>> call = orderApi.getListOrder(mID);

        call.enqueue(new Callback<List<OrderHistoryOverview>>() {
            @Override
            public void onResponse(Call<List<OrderHistoryOverview>> call, Response<List<OrderHistoryOverview>> response) {
                if (response.isSuccessful()) {
                    listOrder.clear();
                    List<OrderHistoryOverview> newList = response.body();
                    Log.e("User OrderHistory", "received");
                    listOrder.addAll(newList);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<OrderHistoryOverview>> call, Throwable t) {

            }
        });
    }

    UserOrderOverviewAdapter.OnChildListener onChildListener = new UserOrderOverviewAdapter.OnChildListener() {
        @Override
        public void onChildClick(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("id", listOrder.get(position).getId());
            NavHostFragment.findNavController(UserOrderHistoryFragment.this).navigate(R.id.action_userOrderHistoryFragment_to_userOrderDetailFragment,bundle);
        }
    };
}