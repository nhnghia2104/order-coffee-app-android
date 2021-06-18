package com.cogeek.tncoffee.ui.menu.item;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.ItemCart;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.cart.CartViewModel;
import com.cogeek.tncoffee.ui.menu.ItemViewModel;
import com.cogeek.tncoffee.utils.NumberHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class ItemBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private TextView txtQty;
    private TextView btnIncrease;
    private TextView btnDecrease;
    private ConstraintLayout btnAddItem;
    private Product product;
    private CartViewModel cartViewModel;
    private ItemViewModel itemViewModel;
    private int itemQty = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        itemViewModel.getSelectedItem().observe(getActivity(), item -> {
            product = item;
        });
        View view = inflater.inflate(R.layout.bottom_sheet_item_layout, container, false);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                FrameLayout bottomSheet = (FrameLayout)
                        dialog.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoundedImageView imageView = view.findViewById(R.id.imageItem_BottomSheet);
        TextView txtName = view.findViewById(R.id.txtItemName_BottomSheet);
        TextView txtPrice = view.findViewById(R.id.txtItemPrice_BottomSheet);
        TextView txtDescription = view.findViewById(R.id.txtItemDescription_BottomSheet);
        ImageView btnImageClose = view.findViewById(R.id.btnImageClose_BottomSheet);
        btnIncrease = view.findViewById(R.id.btnIncreaseQty);
        btnDecrease = view.findViewById(R.id.btnDecreaseQty);
        txtQty = view.findViewById(R.id.txtQuantity);
        btnAddItem = view.findViewById(R.id.btnAddItem);
        //==========================================================
        txtName.setText(product.getName());
        txtPrice.setText(NumberHelper.getInstance().currencyFormat(product.getFinalPrice()));
        txtDescription.setText(product.getDescription());
        Picasso.get()
                .load(product.getImage())
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerInside()
                .into(imageView);
        txtQty.setText(String.valueOf(itemQty));

        btnImageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQty++;
                changeItemQty(itemQty);
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQty = itemQty == 1 ? 1 : itemQty - 1;
                changeItemQty(itemQty);
            }
        });
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToCart();
            }
        });
    }

    private void changeItemQty(int num) {
        txtQty.setText(String.valueOf(num));
//        txtPriceFinal.setText("Chọn món - " + NumberHelper.getInstance().currencyFormat(itemQty * price));
    }

    private void addItemToCart() {
        ItemCart cartDetail = new ItemCart(product.getId(),product.getName(),product.getImage(),product.getPrice(),product.getDiscount(),itemQty);
        cartViewModel.addItem(cartDetail);
        dismiss();
    }
}
