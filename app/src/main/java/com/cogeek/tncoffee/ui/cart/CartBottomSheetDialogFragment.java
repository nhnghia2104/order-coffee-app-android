package com.cogeek.tncoffee.ui.cart;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.CartDetail;
import com.cogeek.tncoffee.ui.menu.MenuViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CartBottomSheetDialogFragment extends BottomSheetDialogFragment {


    private CartViewModel cartViewModel;
    private List<CartDetail> cartDetails = new ArrayList<>();
    private ItemCartAdapter itemCartAdapter;
    private RecyclerView recyclerView;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout pickupLayout = view.findViewById(R.id.pickupLayout);
        pickupLayout.setVisibility(View.GONE);

        recyclerView = view.findViewById(R.id.recyclerView_cart_item);
        itemCartAdapter = new ItemCartAdapter(cartDetails);
        recyclerView.setAdapter(itemCartAdapter);
        registerLiveDataListenner();

    }

    public void registerLiveDataListenner() {
        cartViewModel.getCart().observe(getViewLifecycleOwner(), cart -> {
            cartDetails.clear();
            cartDetails = cart.getDetails();
            itemCartAdapter.setObjects(cartDetails);
            itemCartAdapter.notifyDataSetChanged();
        });
    }
}
