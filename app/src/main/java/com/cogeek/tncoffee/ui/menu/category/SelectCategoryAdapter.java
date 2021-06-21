package com.cogeek.tncoffee.ui.menu.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.home.sale.HorizontalItemAdapter;

import java.util.List;

public class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private OnChildListener onChildListener;

    public SelectCategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public interface OnChildListener {
        void onChildClick(int position);
    }

    public void setOnChildListener(OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale_cell, parent, false);
        return new SelectCategoryAdapter.ViewHolder(v, onChildListener);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.getTxtName().setText("Tất cả danh mục");
        }
        else {
            Category category = categoryList.get(position-1);
            holder.getTxtName().setText(category.getName());
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtName;
        private ImageView imgIcon;
        private OnChildListener onChildListener;
        public ViewHolder(@NonNull View itemView, OnChildListener onChildListener) {
            super(itemView);
            this.onChildListener = onChildListener;
            txtName = itemView.findViewById(R.id.txt_category_name);
            imgIcon = itemView.findViewById(R.id.img_category_icon);
            itemView.setOnClickListener(this);
        }

        public TextView getTxtName() {
            return txtName;
        }

        public ImageView getImgIcon() {
            return imgIcon;
        }

        @Override
        public void onClick(View v) {
            onChildListener.onChildClick(getAdapterPosition());
        }
    }
}
