package com.cogeek.tncoffee.ui.userproduct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.OrderDetail;
import com.cogeek.tncoffee.models.Product;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.ViewHolder> {

    private List<Product> productList;

    public UserProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_product_row, parent, false);
        return new UserProductAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.getTxtOrderDetailName().setText(product.getName());

        Picasso.get()
                .load(product.getImage())
                .fit().centerInside()
                .placeholder(R.drawable.ic_zcafe_hint)
                .into(holder.getImgOrderDetail());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imgOrderDetail;
        private final TextView txtOrderDetailName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderDetailName = itemView.findViewById(R.id.txt_product_detail_name);
            imgOrderDetail = itemView.findViewById(R.id.img_product_user);
        }

        public RoundedImageView getImgOrderDetail() {
            return imgOrderDetail;
        }

        public TextView getTxtOrderDetailName() {
            return txtOrderDetailName;
        }
    }
}