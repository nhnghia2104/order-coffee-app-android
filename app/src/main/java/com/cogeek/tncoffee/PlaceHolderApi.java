package com.cogeek.tncoffee;

import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceHolderApi {

    @GET("product/getProductsByCategories")
    Call<List<Category>> getCategories();


}
