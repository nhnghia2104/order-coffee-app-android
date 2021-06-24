package com.cogeek.tncoffee.ui.userreview;

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

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.ReviewApi;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.menu.item.ChildItemAdapter;
import com.cogeek.tncoffee.ui.userreview.write.WriteReviewDialogFragment;
import com.cogeek.tncoffee.utils.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserReviewProductFragment extends Fragment {

    private static final String ARG_ID = "id";
    private String mID;
    private List<Product> productList;
    private UserReviewProductAdapter adapter;
    private RecyclerView recyclerView;
    private View layoutError;

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
        return inflater.inflate(R.layout.fragment_user_review_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_product_to_review);
        layoutError = view.findViewById(R.id.layout_error);

        productList = new ArrayList<>();
        adapter = new UserReviewProductAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnChildListener(new ChildItemAdapter.OnChildListener() {
            @Override
            public void onChildClick(int position) {
                WriteReviewDialogFragment dialogFragment = new WriteReviewDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("productId", productList.get(position).getId());
                bundle.putString("userId", mID);
                dialogFragment.setArguments(bundle);
                dialogFragment.setOnReviewSendListener(new WriteReviewDialogFragment.OnReviewSendListener() {
                    @Override
                    public void onSent(boolean sent) {
                        if (sent) {
                            loadData();
                        }
                    }
                });
                dialogFragment.show(getActivity().getSupportFragmentManager(),"write review");
            }
        });
        loadData();

        view.findViewById(R.id.btn_close).setOnClickListener(v -> {
            NavHostFragment.findNavController(UserReviewProductFragment.this).popBackStack();
        });
    }

    private void loadData() {
        ReviewApi reviewApi = NetworkProvider.self().retrofit.create(ReviewApi.class);
        Call<List<Product>> call = reviewApi.getListProductShouldReview(mID);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList.clear();
                    List<Product> newList = response.body();
                    productList.addAll(newList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });
    }
}