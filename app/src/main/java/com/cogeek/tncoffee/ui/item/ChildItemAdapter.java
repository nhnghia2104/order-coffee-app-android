package com.cogeek.tncoffee.ui.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.utils.NumberHelper;
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
        holder.getTxtPrice().setText(NumberHelper.getInstance().currencyFormat(item.getPrice()));

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
        private final TextView txtItemName;
        private final TextView txtPrice;
        private final RoundedImageView imageItem;
        private OnChildListener onChildListener;

        public ViewHolder(@NonNull View v, OnChildListener onChildClickListener) {
            super(v);
            txtItemName = v.findViewById(R.id.txtItemName);
            txtPrice = v.findViewById(R.id.txtPrice);
            imageItem = v.findViewById(R.id.imageItem_BottomSheet);
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

        @Override
        public void onClick(View v) {
            if (onChildListener != null) {
                onChildListener.onChildClick(getAdapterPosition());
            }
        }
    }
}
