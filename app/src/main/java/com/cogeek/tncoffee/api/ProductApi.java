package com.cogeek.tncoffee.api;

import com.cogeek.tncoffee.models.Category;
import com.cogeek.tncoffee.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {

    @GET("product/getProductsByCategories")
    Call<List<Category>> getCategories();

    @GET("product/getLimitSaleProducts")
    Call<List<Product>> getLimitSaleProducts();

    @GET("product/getTopProducts")
    Call<List<Product>> getTopProducts();

}
