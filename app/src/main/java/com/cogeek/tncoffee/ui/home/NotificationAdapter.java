package com.cogeek.tncoffee.ui.home;

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
import com.cogeek.tncoffee.models.Notification;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    private Activity context;
    private int resource;
    private List<Notification> objects;
    public NotificationAdapter(@NonNull Context context, int resource, @NonNull List<Notification> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        TextView txtTitle = row.findViewById(R.id.txtTitle);
        TextView txtTime = row.findViewById(R.id.txtTime);
        RoundedImageView imageView = row.findViewById(R.id.img);

        String dateAsText = new SimpleDateFormat("dd/MM/yyyy HH:mm")
                .format(new Date(objects.get(position).getiTime() * 1000L));

        txtTitle.setText(objects.get(position).getTitle());
        txtTime.setText(dateAsText);

        Picasso.get()
                .load(objects.get(position).getImageUrl())
                .placeholder(R.drawable.ic_zcafe_hint)
                .fit()
                .centerCrop()
                .into(imageView);
        return row;
    }
}
