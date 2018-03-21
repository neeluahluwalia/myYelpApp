package com.example.myyelpapp.ui.restaurants;

import com.example.myyelpapp.ui.base.BasePresenter;
import com.example.myyelpapp.ui.base.BaseView;
import com.example.myyelpapp.data.models.Restaurant;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

interface RestaurantDetailContract {
  public interface View extends BaseView<RestaurantDetailContract.Presenter> {
    void showDetails(Restaurant restaurant);

    void showLoading();

    void showError(String message);

    void doneLoading();
  }

  interface Presenter extends BasePresenter {
    void fetchRestaurantDetail(String id);
  }
}
