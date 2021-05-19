package com.cogeek.tncoffee.ui.cart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.CartDetail;
import com.cogeek.tncoffee.models.Store;
import com.cogeek.tncoffee.utils.NumberHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {

    List<CartDetail> objects;

    public ItemCartAdapter(List<CartDetail> objects) {
        this.objects = objects;
    }

    public void setObjects(List<CartDetail> objects) {
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_row,
                        parent,
                        false);
        return new ItemCartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartDetail cartDetail = objects.get(position);

        holder.getTxtItem().setText(cartDetail.getQuantity()
                + " x "
                + cartDetail.getItem().getName());

        holder.getTxtSize().setText(cartDetail.getSize().getDisplayName());


        holder.getTxtTotalPrice().setText(NumberHelper.getInstance().currencyFormat(cartDetail.getPrice()));
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtItem;
        private final TextView txtSize;
        private final TextView txtTotalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem_cart);
            txtSize = itemView.findViewById(R.id.txtItem_size_cart);
            txtTotalPrice = itemView.findViewById(R.id.txtItem_totalPrice_cart);
        }

        public TextView getTxtItem() {
            return txtItem;
        }

        public TextView getTxtSize() {
            return txtSize;
        }

        public TextView getTxtTotalPrice() {
            return txtTotalPrice;
        }
    }
}
