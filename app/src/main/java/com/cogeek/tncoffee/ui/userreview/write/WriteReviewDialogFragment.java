package com.cogeek.tncoffee.ui.userreview.write;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.ReviewApi;
import com.cogeek.tncoffee.models.Review;
import com.cogeek.tncoffee.utils.NetworkProvider;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteReviewDialogFragment extends DialogFragment {
    private EditText txtHead,txtContent;
    private RatingBar ratingBar;
    private Button btnCancel, btnSend;

    private OnReviewSendListener onReviewSendListener;
    public interface OnReviewSendListener {
        void onSent(boolean sent);
    }

    public void setOnReviewSendListener(OnReviewSendListener onReviewSendListener) {
        this.onReviewSendListener = onReviewSendListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_write_review, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtHead = view.findViewById(R.id.edit_head);
        txtContent = view.findViewById(R.id.edit_content);
        ratingBar = view.findViewById(R.id.ratingBar_vote);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnSend = view.findViewById(R.id.btn_send);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReview();
            }
        });
    }

    private void sendReview() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String idProduct = arguments.getString("productId");
            String idUser = arguments.getString("userId");;
            float vote = ratingBar.getRating();
            String head = txtHead.getText().toString();
            String content = txtContent.getText().toString();
            ReviewApi reviewApi = NetworkProvider.self().retrofit.create(ReviewApi.class);
            Call<Review> call = reviewApi.writeReview(idProduct,idUser,vote,head,content,new Date().getTime());
            call.enqueue(new Callback<Review>() {
                @Override
                public void onResponse(Call<Review> call, Response<Review> response) {
                    if (response.isSuccessful()) {
                        onReviewSendListener.onSent(true);
                        dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Review> call, Throwable t) {

                }
            });

        }
    }
}
