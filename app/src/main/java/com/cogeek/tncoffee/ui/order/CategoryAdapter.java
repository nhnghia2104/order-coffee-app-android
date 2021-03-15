package com.cogeek.tncoffee.ui.order;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Category;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    Activity context;
    int resource;
    List<Category> objects;
    ImageView ivResult;
    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
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

        // Find view by Id
        TextView txtTitle = (TextView) row.findViewById(R.id.txtItemName);
        TextView txtDes = (TextView) row.findViewById(R.id.txtItemDescription);
        ivResult = row.findViewById(R.id.imgCategory);

        // Get object from list
        Category category = this.objects.get(position);

        // Set data
        txtTitle.setText(category.getName());
        txtDes.setText(category.getDescription());

//        LoadImage loadImage = new LoadImage(ivResult);
//        loadImage.execute(category.getImageUrl());

        Picasso.get()
                .load("https://raw.githubusercontent.com/nhnghia2104/order-coffee-app-android/master/app/src/main/res/drawable/image_1.jpg")
                .fit()
                .centerCrop()
                .error(R.drawable.image_1)
                .into(ivResult);

        return row;
    }
    private class LoadImage extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView ivResult) {
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivResult.setImageBitmap(bitmap);
        }
    }
}
