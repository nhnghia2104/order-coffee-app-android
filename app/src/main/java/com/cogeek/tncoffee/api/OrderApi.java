package com.cogeek.tncoffee.api;

import com.cogeek.tncoffee.models.Order;
import com.cogeek.tncoffee.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderApi {
    @FormUrlEncoded
    @POST("order/create")
    Call<Order> create(@Field("id") long id,
                       @Field("createAt") String createAt,
                       @Field("total") Double total,
                       @Field("idPayment") int idPayment,
                       @Field("status") int status,
                       @Field("paid") int paid,
                       @Field("payDate") String payDate,
                       @Field("note") String note,
                       @Field("idCustomer") String idCustomer,
                       @Field("timeline") String timeline,
                       @Field("productList") String productList,
                       @Field("tracking") String tracking,
                       @Field("shipping_address") String shipAddress,
                       @Field("shipping_option_id") int shipOption
                       );
}
