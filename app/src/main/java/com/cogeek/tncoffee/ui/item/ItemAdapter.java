package com.cogeek.tncoffee.ui.item;

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
import com.cogeek.tncoffee.models.Item;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    Activity context;
    int resource;
    List<Item> objects;
    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
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

        // Find View
        TextView txtName = row.findViewById(R.id.txtItemName);
        TextView txtDescription = row.findViewById(R.id.txtItemDescription);
        TextView txtPrice = row.findViewById(R.id.txtPrice);
        RoundedImageView imageView = row.findViewById(R.id.imageItem);

        // Find Object
        Item item = this.objects.get(position);

        //
        txtName.setText(item.getName());
        txtDescription.setText(item.getDescription());
        txtPrice.setText(String.valueOf(item.getPrice()));
        Picasso.get()
                .load(item.getImageUrl())
                .fit()
                .centerCrop()
                .into(imageView);

        return row;
    }
}
