package com.cogeek.tncoffee.ui.cart;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.MapsActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.OrderApi;
import com.cogeek.tncoffee.models.ItemCart;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.models_old.CartDetail;
import com.cogeek.tncoffee.models_old.Order;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.cogeek.tncoffee.utils.NumberHelper;
import com.cogeek.tncoffee.utils.SharedHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

public class CartBottomSheetDialogFragment extends BottomSheetDialogFragment {


    private CartViewModel cartViewModel;
    private List<ItemCart> cartDetails = new ArrayList<>();
    private ItemCartAdapter itemCartAdapter;
    private RecyclerView recyclerView;
    private TextView txtAddress;
    private TextView txtPriceFinal;
    private Button btnConfirmCart;
    private Double totalCart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
//        menuViewModel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        View view = inflater.inflate(R.layout.bottom_sheet_cart_layout, container, false);
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

    ItemCartAdapter.OnItemListener onItemListener = new ItemCartAdapter.OnItemListener() {
        @Override
        public void onItemClick(int row) {

        }

        @Override
        public void onIncrease(int row) {
            cartViewModel.addItem(cartDetails.get(row));
        }

        @Override
        public void onDecrease(int row) {
            cartViewModel.removeItem(cartDetails.get(row).getId());
        }

        @Override
        public void onRemove(int row) {
            cartViewModel.deleteItemCart(cartDetails.get(row));
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView_cart_item);
        itemCartAdapter = new ItemCartAdapter(cartDetails);
        recyclerView.setAdapter(itemCartAdapter);
        itemCartAdapter.setOnItemListener(onItemListener);
        registerLiveDataListenner();

        txtAddress = view.findViewById(R.id.txtAddress);
        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                getActivity().startActivity(intent);
            }
        });

        txtPriceFinal = view.findViewById(R.id.txtPriceFinal);
        btnConfirmCart = view.findViewById(R.id.btnConfirmCart);
        btnConfirmCart.setOnClickListener(onConfirmCartListener);
    }

    View.OnClickListener onConfirmCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User user = SharedHelper.getInstance(getActivity()).getUserProfile();
            OrderApi orderApi = NetworkProvider.self().retrofit.create(OrderApi.class);
            orderApi.create(new Date().getTime(),
                    new Date().getTime(),
                    totalCart,
                    1,
                    1,
                    0,
                    0,
                    "",
                    user.getUid(),
                    "",
                    "",
                    "",
                    "",
                    1);
        }
    };

    public void registerLiveDataListenner() {
        cartViewModel.getCart().observe(getViewLifecycleOwner(), cart -> {
            cartDetails.clear();
            cartDetails.addAll(cart.getItemList());
            itemCartAdapter.notifyDataSetChanged();
            txtPriceFinal.setText(cart.totalCartPriceToString());
            totalCart = cart.totalCartPrice();
        });
    }
}
