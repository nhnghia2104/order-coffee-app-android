package com.cogeek.tncoffee.ui.store;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models_old.Store;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoreAdapter extends ArrayAdapter<Store> {
    Activity context;
    int resource;
    List<Store> objects;
    public StoreAdapter(@NonNull Context context, int resource, @NonNull List<Store> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        // Find view
        TextView txtName = row.findViewById(R.id.txtStoreName);
        TextView txtAddress = row.findViewById(R.id.txtStoreAddress);
        TextView txtDistance = row.findViewById(R.id.txtDistance);
        RoundedImageView imgView = row.findViewById(R.id.imageStore);

        Store store = this.objects.get(position);

        // Adding set data code below
        txtName.setText(store.getName());
        txtAddress.setText(store.getAddress());
        txtDistance.setText("Cách đây " + store.getDistance().toString() + "km");
        Picasso.get()
                .load(store.getImageUrl())
                .fit()
                .centerCrop()
                .into(imgView);

        return row;
    }
}
