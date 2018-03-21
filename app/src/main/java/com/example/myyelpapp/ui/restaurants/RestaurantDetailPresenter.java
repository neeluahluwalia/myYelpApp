package com.example.myyelpapp.ui.restaurants;

import com.example.myyelpapp.RetrofitClient;
import com.example.myyelpapp.data.rest.YelpAPI;
import com.example.myyelpapp.data.models.Restaurant;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class RestaurantDetailPresenter implements RestaurantDetailContract.Presenter {
  private RestaurantDetailContract.View view;

  public RestaurantDetailPresenter(RestaurantDetailContract.View view) {
    this.view = view;
  }

  @Override public void fetchRestaurantDetail(String id) {
    YelpAPI yelpAPI = RetrofitClient.getClient().create(YelpAPI.class);
    yelpAPI.getRestaurantDetail(id).enqueue(new Callback<Restaurant>() {
      @Override public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
        if (response.isSuccessful()) {
          view.doneLoading();
          view.showDetails(response.body());
        } else {
          view.doneLoading();
          try {
            // we can show dialogs according to response code received using a helper class
            view.showError(response.errorBody().string());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      @Override public void onFailure(Call<Restaurant> call, Throwable t) {
        view.doneLoading();
        view.showError(t.getMessage());
      }
    });
  }
}

