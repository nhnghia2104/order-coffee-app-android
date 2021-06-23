package com.cogeek.tncoffee.ui.userorder.detail;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.TextView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.OrderApi;
import com.cogeek.tncoffee.models.Order;
import com.cogeek.tncoffee.models.OrderDetail;
import com.cogeek.tncoffee.ui.userorder.UserOrderHistoryFragment;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.cogeek.tncoffee.utils.NumberHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrderDetailFragment extends Fragment {

    private UserOrderDetailViewModel mViewModel;
    private String mID;
    private RecyclerView recyclerView;
    private TextView txtId, txtDateCreated, txtStatus, txtName, txtPhone, txtAddress, txtTotal, txtStatusTracking, txtTrackingTime, txtViewTracking;
    private List<OrderDetail> orderDetails = new ArrayList<>();
    private OrderDetailAdapter adapter;
    private UserOrderDetailViewModel viewModel;

    public static UserOrderDetailFragment newInstance() {
        return new UserOrderDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mID = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(UserOrderDetailViewModel.class);
        return inflater.inflate(R.layout.fragment_user_order_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initial(view);
        addAction();
        OrderApi orderApi = NetworkProvider.self().retrofit.create(OrderApi.class);
        Call<Order> call = orderApi.getOrderById(mID);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Order order = response.body();
                    if (order != null) {
                        fillUiWithData(order);
                    }
                    Log.e("hehe", order.getShipmentDetails().getAddress());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
        view.findViewById(R.id.btn_close).setOnClickListener(v -> {
            NavHostFragment.findNavController(UserOrderDetailFragment.this).popBackStack();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserOrderDetailViewModel.class);

    }

    private void initial(View view ) {
        txtId = view.findViewById(R.id.txt_order_id);
        txtDateCreated = view.findViewById(R.id.txt_order_date_created);
        txtStatus = view.findViewById(R.id.txt_order_status);
        txtStatusTracking = view.findViewById(R.id.txt_order_status_2);
        txtTrackingTime = view.findViewById(R.id.txt_order_status_tracking_time);
        txtName = view.findViewById(R.id.txt_receiver_name);
        txtPhone = view.findViewById(R.id.txt_receiver_phone);
        txtAddress = view.findViewById(R.id.txt_receiver_address);
        txtTotal = view.findViewById(R.id.txt_order_total);
        txtViewTracking = view.findViewById(R.id.txt_go_to_tracking);

        /* ------------------- */
        recyclerView = view.findViewById(R.id.rv_order_detail);
        adapter = new OrderDetailAdapter(orderDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void addAction() {
        txtViewTracking.setOnClickListener(clickViewTracking);
    }

    private void fillUiWithData(Order order) {
        viewModel.selectItem(order);
        txtId.setText("Mã đơn hàng: " + order.getId());
        txtDateCreated.setText("Ngày đặt hàng: " + order.getCreatedFormat());
        txtStatus.setText(order.getStatus());
        txtStatusTracking.setText(order.getTrackingOrderList().get(0).getStatus());
        txtTrackingTime.setText(order.getTrackingOrderList().get(0).getTimeFormat());
        txtName.setText(order.getShipmentDetails().getFullName());
        txtPhone.setText(order.getShipmentDetails().getPhone());
        txtAddress.setText(order.getShipmentDetails().getFullAddress());
        txtTotal.setText(order.getTotal());

        orderDetails.clear();
        orderDetails.addAll(order.getOrderDetailList());
        adapter.notifyDataSetChanged();
    }

    View.OnClickListener clickViewTracking = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavHostFragment.findNavController(UserOrderDetailFragment.this).navigate(R.id.action_userOrderDetailFragment_to_orderTrackingFragment);
        }
    };
}