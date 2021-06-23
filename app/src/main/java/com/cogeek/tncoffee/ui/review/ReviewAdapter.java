package com.cogeek.tncoffee.ui.review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Review;
import com.cogeek.tncoffee.ui.menu.item.ChildItemAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Review review = reviews.get(position);

        Picasso.get()
                .load(review.getAvatar())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .into(holder.getImageUserAvatar());

        holder.getRatingBar().setRating(review.getVote());
        holder.getTxtTitle().setText(review.getHead());
        holder.getTxtContent().setText(review.getContent());
        holder.getTxtUserInfo().setText(String.format("Đăng bởi %s vào lúc %s", review.getName(),review.getDate()));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitle,txtContent, txtUserInfo;
        private final RoundedImageView imageUserAvatar;
        private final RatingBar ratingBar;

        public ViewHolder(@NonNull View v) {
            super(v);
            txtTitle = v.findViewById(R.id.img_user_review_title);
            txtContent = v.findViewById(R.id.txt_user_review_content);
            imageUserAvatar = v.findViewById(R.id.img_user_review_avt);
            txtUserInfo = v.findViewById(R.id.txt_user_review_info);
            ratingBar = v.findViewById(R.id.ratebar_user_review);
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }

        public TextView getTxtContent() {
            return txtContent;
        }

        public TextView getTxtUserInfo() {
            return txtUserInfo;
        }

        public RoundedImageView getImageUserAvatar() {
            return imageUserAvatar;
        }

        public RatingBar getRatingBar() {
            return ratingBar;
        }
    }
}
