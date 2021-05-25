package com.cogeek.tncoffee.ui.cart;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.ItemCart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {

    List<ItemCart> objects;

    public ItemCartAdapter(List<ItemCart> objects) {
        this.objects = objects;
    }

    public void setObjects(List<ItemCart> objects) {
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
        ItemCart item = objects.get(position);

        holder.getTxtItemName().setText(item.getName());
        holder.getTxtQuantity().setText(String.valueOf(item.getQuantity()));
        holder.getTxtFinalPrice().setText(item.getFinalPriceToString());
        if (item.getDiscount() > 0) {
            holder.getTxtPrice().setText(item.getPriceToString());
            holder.getTxtPrice().setPaintFlags(holder.getTxtPrice().getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.getTxtPrice().setText("");
        }

        Picasso.get()
                .load(item.getImage())
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerInside()
                .into(holder.getImgItem());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtItemName;
        private final TextView txtFinalPrice;
        private final TextView txtPrice;
        private final ImageView imgItem;
        private final ImageButton btnDecrease;
        private final ImageButton btnInCrease;
        private final TextView txtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name_cart);
            txtFinalPrice = itemView.findViewById(R.id.txt_item_final_price_cart);
            txtPrice = itemView.findViewById(R.id.txt_item_price_cart);
            imgItem = itemView.findViewById(R.id.img_item_cart);
            btnDecrease = itemView.findViewById(R.id.btn_decrease_cart);
            btnInCrease = itemView.findViewById(R.id.btn_increase_cart);
            txtQuantity = itemView.findViewById(R.id.txt_item_quantity_cart);
        }

        public TextView getTxtFinalPrice() {
            return txtFinalPrice;
        }

        public TextView getTxtPrice() {
            return txtPrice;
        }

        public ImageView getImgItem() {
            return imgItem;
        }

        public ImageButton getBtnDecrease() {
            return btnDecrease;
        }

        public ImageButton getBtnInCrease() {
            return btnInCrease;
        }

        public TextView getTxtQuantity() {
            return txtQuantity;
        }

        public TextView getTxtItemName() {
            return txtItemName;
        }
    }
}
