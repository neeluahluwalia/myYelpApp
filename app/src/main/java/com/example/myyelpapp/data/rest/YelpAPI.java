package com.example.myyelpapp.data.rest;

import com.example.myyelpapp.data.models.BusinessResponseClass;
import com.example.myyelpapp.data.models.Restaurant;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public interface YelpAPI {
  @Headers("Content-Type: application/json") @GET("businesses/search")
  Call<BusinessResponseClass> getRestaurantList(@QueryMap Map<String, String> queryParams);

  @Headers("Content-Type: application/json") @GET("businesses/{id}/reviews")
  Call<BusinessResponseClass> getReviewsList(@Path(value = "id", encoded = true) String id);

  @Headers("Content-Type: application/json") @GET("businesses/{id}")
  Call<Restaurant> getRestaurantDetail(@Path(value = "id", encoded = true) String id);
}
