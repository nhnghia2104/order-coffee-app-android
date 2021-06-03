package com.cogeek.tncoffee.api;

import com.cogeek.tncoffee.models.OrderHistoryOverview;
import com.cogeek.tncoffee.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @FormUrlEncoded
    @POST("user/authenticate")
    Call<User> authenticate(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/getListOrder")
    Call<List<OrderHistoryOverview>> getListOrder(@Field("id") String id);
}
