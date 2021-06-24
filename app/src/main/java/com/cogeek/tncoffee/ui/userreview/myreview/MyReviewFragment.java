package com.cogeek.tncoffee.ui.userreview.myreview;

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
import com.cogeek.tncoffee.api.ReviewApi;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.models.Review;
import com.cogeek.tncoffee.ui.userreview.UserReviewProductAdapter;
import com.cogeek.tncoffee.utils.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReviewFragment extends Fragment {

    private static final String ARG_ID = "id";
    private String mID;
    private List<Review> reviews;
    private MyReviewAdapter adapter;
    private RecyclerView recyclerView;
    private View layoutError;

    public MyReviewFragment() {
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
        return inflater.inflate(R.layout.fragment_my_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_my_review);
        layoutError = view.findViewById(R.id.layout_error);

        reviews = new ArrayList<>();
        adapter = new MyReviewAdapter(reviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        loadData();

        view.findViewById(R.id.btn_close).setOnClickListener(v -> {
            NavHostFragment.findNavController(MyReviewFragment.this).popBackStack();
        });

    }

    private void loadData() {
        ReviewApi reviewApi = NetworkProvider.self().retrofit.create(ReviewApi.class);
        Call<List<Review>> call = reviewApi.getListReviewOfCustomer(mID);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    reviews.clear();
                    List<Review> newList = response.body();
                    reviews.addAll(newList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });
    }
}