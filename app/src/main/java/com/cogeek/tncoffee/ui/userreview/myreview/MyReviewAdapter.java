package com.cogeek.tncoffee.ui.userreview.myreview;

import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Review;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ViewHolder> {

    private List<Review> reviewList;

    public MyReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public MyReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_review_row, parent, false);
        return new MyReviewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReviewAdapter.ViewHolder holder, int position) {
        Review review = reviewList.get(position);

        if (review.getContent().isEmpty() ) {
            holder.getTxtContent().setVisibility(View.GONE);
        }
        holder.getTxtContent().setText(review.getContent());
        holder.getTxtProductName().setText(review.getProductName());
        holder.getTxtTitle().setText(review.getHead());
        holder.getTxtReviewDate().setText(review.getDate());
        holder.getRatingBar().setRating(review.getVote());

        Picasso.get()
                .load(review.getProductImage())
                .fit().centerInside()
                .placeholder(R.drawable.ic_zcafe_hint)
                .into(holder.getImgProduct());


    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtProductName, txtReviewDate,  txtTitle, txtContent;
        private final ImageView imgProduct;
        private final RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txt_product_review_name);
            txtReviewDate = itemView.findViewById(R.id.txt_review_date);
            txtTitle = itemView.findViewById(R.id.txt_review_title);
            txtContent = itemView.findViewById(R.id.txt_review_content);
            imgProduct = itemView.findViewById(R.id.img_product_review);
            ratingBar = itemView.findViewById(R.id.ratebar_review);
        }

        public TextView getTxtProductName() {
            return txtProductName;
        }

        public TextView getTxtReviewDate() {
            return txtReviewDate;
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }

        public TextView getTxtContent() {
            return txtContent;
        }

        public ImageView getImgProduct() {
            return imgProduct;
        }

        public RatingBar getRatingBar() {
            return ratingBar;
        }
    }
}