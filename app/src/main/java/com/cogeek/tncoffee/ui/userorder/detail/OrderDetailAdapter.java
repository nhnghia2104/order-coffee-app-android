package com.cogeek.tncoffee.ui.userorder.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.OrderDetail;
import com.cogeek.tncoffee.ui.userorder.UserOrderOverviewAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private List<OrderDetail> orderDetailList;

    public OrderDetailAdapter(List<OrderDetail> orderDetails) {
        this.orderDetailList = orderDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail_row, parent, false);
        return new OrderDetailAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail detail = orderDetailList.get(position);

        holder.getTxtOrderDetailName().setText(detail.getProductName());
        holder.getTxtOrderDetailPrice().setText(detail.getSalePriceToString() + " x " + detail.getAmount());

        Picasso.get()
                .load(detail.getImage())
                .fit().centerInside()
                .placeholder(R.drawable.ic_zcafe_hint)
                .into(holder.getImgOrderDetail());
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imgOrderDetail;
        private final TextView txtOrderDetailName, txtOrderDetailPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderDetailName = itemView.findViewById(R.id.txt_order_detail_name);
            txtOrderDetailPrice = itemView.findViewById(R.id.txt_order_detail_price);
            imgOrderDetail = itemView.findViewById(R.id.img_order_detail);
        }

        public RoundedImageView getImgOrderDetail() {
            return imgOrderDetail;
        }

        public TextView getTxtOrderDetailName() {
            return txtOrderDetailName;
        }

        public TextView getTxtOrderDetailPrice() {
            return txtOrderDetailPrice;
        }
    }
}
