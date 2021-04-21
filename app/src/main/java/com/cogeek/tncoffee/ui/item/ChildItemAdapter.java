package com.cogeek.tncoffee.ui.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Item;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ViewHolder> {

    private List<Item> itemList;

    public ChildItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false);
        return new ChildItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.getTxtItemName().setText(item.getName());
        holder.getTxtItemDescription().setText(item.getDescription());
        holder.getTxtPrice().setText(item.getPrice() + ".000đ");

        Picasso.get()
                .load(item.getImageUrl())
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerCrop()
                .into(holder.getImageItem());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtItemName;
        private final TextView txtItemDescription;
        private final TextView txtPrice;
        private final RoundedImageView imageItem;

        public ViewHolder(@NonNull View v) {
            super(v);
            txtItemName = v.findViewById(R.id.txtItemName);
            txtItemDescription = v.findViewById(R.id.txtItemDescription);
            txtPrice = v.findViewById(R.id.txtPrice);
            imageItem = v.findViewById(R.id.imageItem);
        }

        public TextView getTxtItemName() {
            return txtItemName;
        }

        public TextView getTxtItemDescription() {
            return txtItemDescription;
        }

        public TextView getTxtPrice() {
            return txtPrice;
        }

        public RoundedImageView getImageItem() {
            return imageItem;
        }
    }
}
