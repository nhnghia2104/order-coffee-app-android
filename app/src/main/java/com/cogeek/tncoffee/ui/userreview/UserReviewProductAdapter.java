package com.cogeek.tncoffee.ui.userreview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Product;
import com.cogeek.tncoffee.ui.menu.item.ChildItemAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserReviewProductAdapter extends RecyclerView.Adapter<UserReviewProductAdapter.ViewHolder> {

    private List<Product> productList;
    private ChildItemAdapter.OnChildListener onChildListener;

    public interface OnChildListener {
        void onChildClick(int position);
    }

    public void setOnChildListener(ChildItemAdapter.OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    public UserReviewProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public UserReviewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_need_review, parent, false);
        return new UserReviewProductAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserReviewProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.getTxtOrderDetailName().setText(product.getName());

        Picasso.get()
                .load(product.getImage())
                .fit().centerInside()
                .placeholder(R.drawable.ic_zcafe_hint)
                .into(holder.getImgOrderDetail());

        holder.getBtnWrite().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChildListener != null) {
                    onChildListener.onChildClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imgOrderDetail;
        private final TextView txtOrderDetailName;
        private final Button btnWrite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderDetailName = itemView.findViewById(R.id.txt_product_should_review_name);
            imgOrderDetail = itemView.findViewById(R.id.img_product_should_review);
            btnWrite = itemView.findViewById(R.id.btn_write_review);
        }

        public Button getBtnWrite() {
            return btnWrite;
        }

        public RoundedImageView getImgOrderDetail() {
            return imgOrderDetail;
        }

        public TextView getTxtOrderDetailName() {
            return txtOrderDetailName;
        }
    }
}