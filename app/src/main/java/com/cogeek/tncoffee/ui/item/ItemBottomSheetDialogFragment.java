package com.cogeek.tncoffee.ui.item;

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

import com.cogeek.tncoffee.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class ItemBottomSheetDialogFragment extends BottomSheetDialogFragment {
    String imageUrl;
    String name;
    int price;
    String description;
    boolean isFavorite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);


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

        Bundle arguments = getArguments();
        if (arguments != null) {
            imageUrl = arguments.getString("imageUrl");
            name = arguments.getString("name");
            description = arguments.getString("description");
            price = arguments.getInt("price");
        }

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


        txtName.setText(name);
        txtPrice.setText(price + ".000đ");
        txtDescription.setText(description);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerCrop()
                .into(imageView);

        ImageView btnImageClose = view.findViewById(R.id.btnImageClose_BottomSheet);
        btnImageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }
}
