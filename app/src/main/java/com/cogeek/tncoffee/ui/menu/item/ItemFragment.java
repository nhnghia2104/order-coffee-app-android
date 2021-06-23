package com.cogeek.tncoffee.ui.menu.item;

import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.ItemCart;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.cart.CartViewModel;
import com.cogeek.tncoffee.ui.menu.ItemViewModel;
import com.cogeek.tncoffee.ui.review.ReviewBottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

public class ItemFragment extends Fragment {

    private Product product;
    private CartViewModel cartViewModel;
    private ItemViewModel itemViewModel;
    private TextView txtOldPrice, txtName, txtPrice, txtDescription, txtRatingValue;
    private ImageView imageView;
    private Button btnAddToCart;
    private ImageButton btnClose;
    private RatingBar ratingBar;
    private View layoutReview;

    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        itemViewModel.getSelectedItem().observe(getActivity(), item -> {
            product = item;
        });
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ObjectAnimator animation = ObjectAnimator.ofFloat(view.findViewById(R.id.ahihi), "translationY", 100f);
//        animation.setDuration(500);
//        animation.start();
        imageView = view.findViewById(R.id.img_product_detail);
        txtOldPrice = view.findViewById(R.id.txt_product_old_price);
        txtOldPrice.setPaintFlags(txtOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        btnAddToCart = view.findViewById(R.id.btn_add_to_cart);
        txtName = view.findViewById(R.id.txt_product_name);
        txtPrice = view.findViewById(R.id.txt_product_price);
        txtDescription = view.findViewById(R.id.txt_product_des);
        txtRatingValue = view.findViewById(R.id.txt_rating_item_des);
        ratingBar = view.findViewById(R.id.rating_item_value);
        btnClose = view.findViewById(R.id.btn_close);
        layoutReview = view.findViewById(R.id.layout_review);

        /* =========================================== */

        /* =========================================== */

        fillData();
        addEvent();
    }

    private void fillData() {
        if (product == null) return;
        Picasso.get()
                .load(product.getImage())
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerInside()
                .into(imageView);

        txtName.setText(product.getName());
        txtOldPrice.setText(product.getDiscount() > 0 ? product.getPriceToString() : "");
        txtPrice.setText(product.getFinalPriceToString());
        txtDescription.setText(product.getDescription());
        ratingBar.setRating(product.getAvg());
        txtRatingValue.setText(String.format("(%d reviews)",product.getAmountVote()));
        btnAddToCart.setText(String.format("| %s",product.getFinalPriceToString()));
    }

    private void addEvent() {
        btnClose.setOnClickListener(v -> {
            NavHostFragment.findNavController(ItemFragment.this).popBackStack();
        });

        btnAddToCart.setOnClickListener(v -> {
            addItemToCart();
        });

        layoutReview.setOnClickListener(v -> {
//            NavHostFragment.findNavController(ItemFragment.this).navigate(R.id.action_item_to_review);
            ReviewBottomSheetDialogFragment bottomSheetDialog = new ReviewBottomSheetDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("productId", product.getId());
            bottomSheetDialog.setArguments(bundle);
            bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Review");
        });

    }

    private void addItemToCart() {
        ItemCart cartDetail = new ItemCart(product.getId(),product.getName(),product.getImage(),product.getPrice(),product.getDiscount(),1);
        cartViewModel.addItem(cartDetail);
        NavHostFragment.findNavController(ItemFragment.this).popBackStack();
    }
}