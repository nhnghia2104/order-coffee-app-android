package com.cogeek.tncoffee.api;

import com.cogeek.tncoffee.models.OrderHistoryOverview;
import com.cogeek.tncoffee.models.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ReviewApi {
    @FormUrlEncoded
    @POST("review/getListReviewByProductId")
    Call<List<Review>> getListReviewByProductId(@Field("id") String id);
}
