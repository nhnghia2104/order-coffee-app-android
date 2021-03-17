package com.cogeek.tncoffee.ui.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cogeek.tncoffee.R;
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
        return inflater.inflate(R.layout.bottom_sheet_item_layout,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL,R.style.BottomSheetTheme);
    }

//    @Override
//    public int getTheme() {
//        return R.style.DialogThemeFullScreen;
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoundedImageView imageView = view.findViewById(R.id.imageItem);
        Picasso.get()
                .load(R.drawable.image_1)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
