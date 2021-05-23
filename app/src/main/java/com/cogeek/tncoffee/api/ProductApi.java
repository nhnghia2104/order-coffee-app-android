package com.cogeek.tncoffee.api;

import com.cogeek.tncoffee.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {

    @GET("product/getProductsByCategories")
    Call<List<Category>> getCategories();

}
