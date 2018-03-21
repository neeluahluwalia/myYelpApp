package com.example.myyelpapp.ui.reviews;

import com.example.myyelpapp.RetrofitClient;
import com.example.myyelpapp.data.rest.YelpAPI;
import com.example.myyelpapp.data.models.BusinessResponseClass;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class ReviewListingPresenter implements ReviewListingContract.Presenter {
  private ReviewListingContract.View view;

  public ReviewListingPresenter(ReviewListingContract.View view) {
    this.view = view;
  }

  @Override public void fetchReviewList(String restaurantId) {
    YelpAPI yelpAPI = RetrofitClient.getClient().create(YelpAPI.class);
    yelpAPI.getReviewsList(restaurantId).enqueue(new Callback<BusinessResponseClass>() {
      @Override public void onResponse(Call<BusinessResponseClass> call,
          Response<BusinessResponseClass> response) {
        if (response.isSuccessful()) {
          view.showReviewList(response.body().getReviews());
        } else {
          try {
            view.showError(response.errorBody().string());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      @Override public void onFailure(Call<BusinessResponseClass> call, Throwable t) {
        view.showError(t.getMessage());
      }
    });
  }
}
