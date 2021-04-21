package com.cogeek.tncoffee.ui.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Item;

import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ViewHolder> {

    private List<Category> categoryList;

    public MainItemAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section,
                parent,
                false);
        return new MainItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);

        String sectionName = category.getName();
        List<Item> items = category.getItems();

        holder.getTxtSectionName().setText(sectionName);
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(items);
        holder.getChildRecyclerView().setAdapter(childItemAdapter);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtSectionName;
        private final RecyclerView childRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSectionName = itemView.findViewById(R.id.txtSectionName);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
        }

        public TextView getTxtSectionName() {
            return txtSectionName;
        }

        public RecyclerView getChildRecyclerView() {
            return childRecyclerView;
        }
    }
}
