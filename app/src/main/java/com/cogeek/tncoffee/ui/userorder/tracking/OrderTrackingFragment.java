package com.cogeek.tncoffee.ui.userorder.tracking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.TimelineOrder;
import com.cogeek.tncoffee.ui.userorder.detail.UserOrderDetailViewModel;

import java.util.List;

public class OrderTrackingFragment extends Fragment {

    private UserOrderDetailViewModel viewModel;
    private List<TimelineOrder> timelineOrderList;
    private  OrderTrackingAdapter adapter;
    private RecyclerView recyclerView;

    public OrderTrackingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(UserOrderDetailViewModel.class);
        viewModel.getSelectedItem().observe(getActivity(), item -> {
            timelineOrderList = item.getTimelineOrderList();
        });
        return inflater.inflate(R.layout.fragment_order_tracking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_order_tracking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderTrackingAdapter(timelineOrderList);
        recyclerView.setAdapter(adapter);

    }
}