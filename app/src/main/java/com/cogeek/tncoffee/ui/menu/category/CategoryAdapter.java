package com.cogeek.tncoffee.ui.menu.category;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.CategoryItem;
import com.cogeek.tncoffee.models_old.Notification;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryItem> {
    private Activity context;
    private int resource;
    List<CategoryItem> objects;
    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<CategoryItem> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }

    private View initView(int position) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        ImageView img = row.findViewById(R.id.img_category_spinner_icon);
        TextView txt = row.findViewById(R.id.txt_category_spinner_name);

        CategoryItem item = objects.get(position);
        if (item != null) {
//            img.setImageResource(item.getIcon());
            txt.setText(item.getName());
        }
        return row;
    }
}
