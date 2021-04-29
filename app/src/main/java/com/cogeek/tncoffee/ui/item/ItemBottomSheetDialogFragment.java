package com.cogeek.tncoffee.ui.item;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private String imageUrl;
    private String name;
    private int price;
    private String description;
    private boolean isFavorite;
    private TextView txtPriceFinal;
    private TextView txtNameFinal;
    private RadioGroup radioGroupItemSize;
    private TextView txtQty;
    private ImageView btnIncrease;
    private ImageView btnDecrease;
    private Button btnConfirmItem;

    private int itemQty = 1;
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

        txtNameFinal = view.findViewById(R.id.txtNameFinal);
        txtPriceFinal = view.findViewById(R.id.txtPriceFinal);
        RoundedImageView imageView = view.findViewById(R.id.imageItem_BottomSheet);
        TextView txtName = view.findViewById(R.id.txtItemName_BottomSheet);
        TextView txtPrice = view.findViewById(R.id.txtItemPrice_BottomSheet);
        TextView txtDescription = view.findViewById(R.id.txtItemDescription_BottomSheet);
        ImageView btnImageClose = view.findViewById(R.id.btnImageClose_BottomSheet);
        radioGroupItemSize = view.findViewById(R.id.radioGroupItemSize);
        btnIncrease = view.findViewById(R.id.btnIncreaseQty);
        btnDecrease = view.findViewById(R.id.btnDecreaseQty);
        txtQty = view.findViewById(R.id.txtQuantity);
        btnConfirmItem = view.findViewById(R.id.btnConfirmItem);
        //==========================================================
        txtName.setText(name);
        txtPrice.setText(price + ".000đ");
        txtDescription.setText(description);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerCrop()
                .into(imageView);
        txtQty.setText(String.valueOf(itemQty));
        txtPriceFinal.setText(String.valueOf(itemQty*price) + ".000đ");
        RadioButton btnSizeSelected = view.findViewById(radioGroupItemSize.getCheckedRadioButtonId());
        txtNameFinal.setText(name + " (" + btnSizeSelected.getText() + ")");
        btnImageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        radioGroupItemSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btnSizeSelected = view.findViewById(checkedId);
                txtNameFinal.setText(name + " (" + btnSizeSelected.getText() + ")");
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
        btnConfirmItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void changeItemQty(int num) {
        txtQty.setText(String.valueOf(num));
        txtPriceFinal.setText(String.valueOf(itemQty*price) + ".000đ");
    }
}
