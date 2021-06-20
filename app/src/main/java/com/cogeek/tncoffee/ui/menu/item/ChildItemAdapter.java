package com.cogeek.tncoffee.ui.menu.item;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Product;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ViewHolder> {

    private List<Product> itemList;
    private OnChildListener onChildListener;

    public interface OnChildListener {
        void onChildClick(int position);
    }

    public void setOnChildListener(OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    public ChildItemAdapter(List<Product> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new ChildItemAdapter.ViewHolder(v, onChildListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = itemList.get(position);
        holder.getTxtItemName().setText(item.getName());
        holder.getTxtPrice().setText(item.getFinalPriceToString());
        holder.getTxtDiscount().setText(item.getDiscountToString());

        Picasso.get()
                .load(item.getImage())
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerInside()
                .into(holder.getImageItem());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtItemName,txtPrice,txtDiscount;
        private final RoundedImageView imageItem;
        private OnChildListener onChildListener;

        public ViewHolder(@NonNull View v, OnChildListener onChildClickListener) {
            super(v);
            txtItemName = v.findViewById(R.id.txt_cell_name);
            txtPrice = v.findViewById(R.id.txt_cell_price);
            imageItem = v.findViewById(R.id.img_item_cell);
            txtDiscount = v.findViewById(R.id.txt_cell_discount);
//            txtDiscount.setPaintFlags(txtDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            this.onChildListener = onChildClickListener;
            v.setOnClickListener(this);
        }

        public TextView getTxtItemName() {
            return txtItemName;
        }

        public TextView getTxtPrice() {
            return txtPrice;
        }

        public RoundedImageView getImageItem() {
            return imageItem;
        }

        public TextView getTxtDiscount() {
            return txtDiscount;
        }

        @Override
        public void onClick(View v) {
            if (onChildListener != null) {
                onChildListener.onChildClick(getAdapterPosition());
            }
        }
    }
}
