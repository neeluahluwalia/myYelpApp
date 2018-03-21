package com.example.myyelpapp.ui.restaurants;

import com.example.myyelpapp.Config;
import com.example.myyelpapp.RetrofitClient;
import com.example.myyelpapp.data.rest.YelpAPI;
import com.example.myyelpapp.data.models.BusinessResponseClass;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class RestaurantListingPresenter implements RestaurantListingContract.Presenter {
  private RestaurantListingContract.View view;

  public RestaurantListingPresenter(RestaurantListingContract.View view) {
    this.view = view;
  }

  @Override public void fetchRestaurantList(String searchText, String sortBy) {
    Map<String, String> data = new HashMap<>();
    data.put("term", Config.DEFAULT_SEARCH_TERM_RESTAURANTS);
    data.put("location", Config.DEFAULT_LOCATION);
    data.put("categories", searchText);
    data.put("sort_by", sortBy);
    data.put("limit", Config.DEFAULT_RESULT_COUNT);
    YelpAPI yelpAPI = RetrofitClient.getClient().create(YelpAPI.class);
    yelpAPI.getRestaurantList(data).enqueue(new Callback<BusinessResponseClass>() {
      @Override public void onResponse(Call<BusinessResponseClass> call,
          Response<BusinessResponseClass> response) {
        if (response.isSuccessful()) {
          view.showRestaurantList(response.body().getBusinesses());
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

      @Override public void onFailure(Call<BusinessResponseClass> call, Throwable t) {
        view.doneLoading();
        view.showError(t.getMessage());
      }
    });
  }

  @Override public void fetchRestaurantByLocation(String location) {
    Map<String, String> data = new HashMap<>();
    data.put("term", Config.DEFAULT_SEARCH_TERM_RESTAURANTS);
    data.put("location", location);
    data.put("limit", Config.DEFAULT_RESULT_COUNT);
    YelpAPI yelpAPI = RetrofitClient.getClient().create(YelpAPI.class);
    yelpAPI.getRestaurantList(data).enqueue(new Callback<BusinessResponseClass>() {
      @Override public void onResponse(Call<BusinessResponseClass> call,
          Response<BusinessResponseClass> response) {
        if (response.isSuccessful()) {
          view.showRestaurantList(response.body().getBusinesses());
        } else {
          view.doneLoading();
          try {
            view.showError(response.errorBody().string());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      @Override public void onFailure(Call<BusinessResponseClass> call, Throwable t) {
        view.doneLoading();
        view.showError(t.getMessage());
      }
    });
  }
}
