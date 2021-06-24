package com.cogeek.tncoffee.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.cogeek.tncoffee.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private String[] imageUrls;
    ViewPager2 viewPager2;

    public SliderAdapter(String[] imageUrls, ViewPager2 viewPager2) {
        this.imageUrls = imageUrls;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(imageUrls[position]);
//        if (position == sliderItemList.size() - 2) {
//            viewPager2.post(runnable);
//        }
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(String url) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_zcafe_hint)
                    .fit()
                    .centerCrop()
                    .into(imageView);
//            imageView.setImageBitmap(sliderItem.getBitmap());
        }
    }

    //
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            sliderItemList.addAll(sliderItemList);
//            notifyDataSetChanged();
//        }
//    };

}
