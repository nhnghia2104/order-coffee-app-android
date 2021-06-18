package com.cogeek.tncoffee.ui.userorder;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.OrderHistoryOverview;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserOrderOverviewAdapter extends RecyclerView.Adapter<UserOrderOverviewAdapter.ViewHolder> {

    private List<OrderHistoryOverview> orderHistoryOverviewList;
    private OnChildListener onChildListener;

    public interface OnChildListener {
        void onChildClick(int position);
    }

    public void setOnChildListener(OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    public UserOrderOverviewAdapter(List<OrderHistoryOverview> orderHistoryOverviewList) {
        this.orderHistoryOverviewList = orderHistoryOverviewList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history_row, parent, false);
        return new ViewHolder(v, onChildListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderHistoryOverview item = orderHistoryOverviewList.get(position);
        holder.getTxtOrderOverviewStatus().setText(item.getStatusName());
        holder.getTxtOrderOverviewStatus().setTextColor(Color.parseColor("#" + item.getStatusColor()));
        if (item.getProductInCart() > 1) {
            holder.getTxtOrderOverviewName().setText(item.getProductName() + " và " + (item.getProductInCart() - 1) + " sản phẩm");
        }
        else {
            holder.getTxtOrderOverviewName().setText(item.getProductName());
        }
        holder.getTxtOrderOverviewDescription().setText(item.getProductInCart() + " sản phẩm | " + item.getTotal());

        Picasso.get()
                .load(item.getProductImage())
                .centerInside()
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .into(holder.getImgOrderOverview());
    }

    @Override
    public int getItemCount() {
        return orderHistoryOverviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtOrderOverviewName;
        private final TextView txtOrderOverviewStatus;
        private final TextView txtOrderOverviewDescription;
        private final RoundedImageView imgOrderOverview;
        private OnChildListener onChildListener;

        public ViewHolder(@NonNull View itemView, OnChildListener onChildClickListener) {
            super(itemView);
            txtOrderOverviewName = itemView.findViewById(R.id.txt_orderoverview_name);
            txtOrderOverviewStatus = itemView.findViewById(R.id.txt_orderoverview_status);
            txtOrderOverviewDescription = itemView.findViewById(R.id.txt_orderoverview_description);
            imgOrderOverview = itemView.findViewById(R.id.img_orderoverview);

            this.onChildListener = onChildClickListener;
            itemView.setOnClickListener(this);
        }

        public TextView getTxtOrderOverviewName() {
            return txtOrderOverviewName;
        }

        public TextView getTxtOrderOverviewStatus() {
            return txtOrderOverviewStatus;
        }

        public TextView getTxtOrderOverviewDescription() {
            return txtOrderOverviewDescription;
        }

        public RoundedImageView getImgOrderOverview() {
            return imgOrderOverview;
        }

        @Override
        public void onClick(View v) {
            if (onChildListener != null) {
                onChildListener.onChildClick(getAdapterPosition());
            }
        }
    }
}
