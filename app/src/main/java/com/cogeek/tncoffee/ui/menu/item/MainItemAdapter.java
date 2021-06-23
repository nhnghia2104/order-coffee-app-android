package com.cogeek.tncoffee.ui.menu.item;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ViewHolder> {

    private List<com.cogeek.tncoffee.models.Category> categoryList;
//    private RecyclerView.LayoutManager layoutManager;
    private  OnItemListener onItemListener;
    private ViewGroup parent;

    public interface OnItemListener {
        void onItemClick(int section, int row);
    };

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public MainItemAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

//    public void setObjects(List<Category> list) {
//        this.categoryList = list;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section,
                parent,
                false);
//        layoutManager = new GridLayoutManager(parent.getContext(),2);
        this.parent = parent;
        return new MainItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int section) {
        Category category = categoryList.get(section);

        String sectionName = category.getName();
        List<Product> items = new ArrayList<>();

        items.addAll(category.getItems());

//        holder.getTxtSectionName().setText(sectionName);
        holder.getTxtSectionName().setVisibility(View.GONE);
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(items);
        childItemAdapter.setOnChildListener(new ChildItemAdapter.OnChildListener() {
            @Override
            public void onChildClick(int row) {
                Log.i("Ngon", "section" + section + ", row:" + row);
                if (onItemListener != null) {
                    onItemListener.onItemClick(section,row);
                }
            }
        });
        holder.getChildRecyclerView().setLayoutManager(new GridLayoutManager(parent.getContext(),2));
        holder.getChildRecyclerView().setAdapter(childItemAdapter);
        childItemAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtSectionName;
        private final RecyclerView childRecyclerView;
        private OnItemListener onItemListener;
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
