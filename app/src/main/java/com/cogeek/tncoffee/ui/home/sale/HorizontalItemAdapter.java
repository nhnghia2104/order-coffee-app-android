package com.cogeek.tncoffee.ui.home.sale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizontalItemAdapter extends RecyclerView.Adapter<HorizontalItemAdapter.ViewHolder> {

    private List<Product> productList;
    private OnChildListener onChildListener;

    public interface OnChildListener {
        void onChildClick(int position);
    }

    public void setOnChildListener(OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    public HorizontalItemAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale_cell, parent, false);
        return new HorizontalItemAdapter.ViewHolder(v, onChildListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.getTxtPrice().setText(product.getFinalPriceToString());
        if (product.getDiscount() > 0) {
            holder.getTxtDiscount().setText(product.getDiscountToString());
        }
        else {
            holder.hideBadgeLayout();
        }

        Picasso.get()
                .load(product.getImage())
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_zcafe_hint)
                .into(holder.getImgProduct());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgProduct;
        private TextView txtPrice, txtDiscount;
        private View layoutBadge;
        private OnChildListener onChildListener;

        public ViewHolder(@NonNull View itemView, OnChildListener onChildListener) {
            super(itemView);
            this.onChildListener = onChildListener;
            imgProduct = itemView.findViewById(R.id.img_sale_product);
            txtDiscount = itemView.findViewById(R.id.txt_sale_discount);
            txtPrice = itemView.findViewById(R.id.txt_sale_price);
            layoutBadge = itemView.findViewById(R.id.layout_badge);
            itemView.setOnClickListener(this);
        }

        public void hideBadgeLayout() {
            layoutBadge.setVisibility(View.GONE);
        }

        public ImageView getImgProduct() {
            return imgProduct;
        }

        public TextView getTxtPrice() {
            return txtPrice;
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
