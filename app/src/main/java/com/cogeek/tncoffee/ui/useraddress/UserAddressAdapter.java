package com.cogeek.tncoffee.ui.useraddress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Address;
import com.cogeek.tncoffee.models.UserAddress;
import com.cogeek.tncoffee.ui.menu.item.ChildItemAdapter;
import com.cogeek.tncoffee.ui.userreview.UserReviewProductAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class UserAddressAdapter extends RecyclerView.Adapter<UserAddressAdapter.ViewHolder> {

    private UserAddress userAddress;
    private OnChildListener onChildListener;

    public interface OnChildListener {
        void onChildClick(int position);
    }

    public void setOnChildListener(OnChildListener onChildListener) {
        this.onChildListener = onChildListener;
    }

    public UserAddressAdapter(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_address, parent, false);
        return new ViewHolder(v, onChildListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address address = userAddress.getAddressList().get(position);
        holder.getTxtAddress().setText(address.getAddress() + ", " +address.getCity());
        holder.getTxtName().setText(address.getFirstName() + " " +address.getLastName());
        holder.getTxtPhone().setText(address.getPhone());

        if (userAddress.getAddressDefaultIndex() == position) {
            holder.setDefault();
        }
    }

    @Override
    public int getItemCount() {
        return userAddress.getAddressList() != null ? userAddress.getAddressList().size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtName, txtPhone, txtAddress;
        private final View layoutIsDefault;
        private OnChildListener onChildListener;
        public ViewHolder(@NonNull View itemView, OnChildListener onChildListener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_address_name);
            txtPhone = itemView.findViewById(R.id.txt_address_phone);
            txtAddress = itemView.findViewById(R.id.txt_address);
            layoutIsDefault = itemView.findViewById(R.id.layout_is_default);
            this.onChildListener = onChildListener;
            itemView.setOnClickListener(this);
        }

        public void setDefault() {
            layoutIsDefault.setVisibility(View.VISIBLE);
        }

        public TextView getTxtName() {
            return txtName;
        }

        public TextView getTxtPhone() {
            return txtPhone;
        }

        public TextView getTxtAddress() {
            return txtAddress;
        }

        @Override
        public void onClick(View v) {
            if (onChildListener != null) {
                onChildListener.onChildClick(getAdapterPosition());
            }
        }
    }
}
