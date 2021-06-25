package com.cogeek.tncoffee.api;

import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductApi {

    @GET("product/getProductsByCategories")
    Call<List<Category>> getProducts();

    @GET("product/getCategory")
    Call<List<Category>> getCategories();

    @GET("product/getLimitSaleProducts")
    Call<List<Product>> getLimitSaleProducts();

    @GET("product/getTopProducts")
    Call<List<Product>> getTopProducts();

    @FormUrlEncoded
    @POST("product/getListCustomerBought")
    Call<List<Product>> getListCustomerBought(@Field("id") String id);

}
