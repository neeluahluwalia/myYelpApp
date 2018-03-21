package com.example.myyelpapp.ui.restaurants;

import com.example.myyelpapp.ui.base.BasePresenter;
import com.example.myyelpapp.ui.base.BaseView;
import com.example.myyelpapp.data.models.Restaurant;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public interface RestaurantListingContract {

  interface View extends BaseView<Presenter> {
    void showRestaurantList(ArrayList<Restaurant> listRestaurants);

    void showLoading();

    void showError(String message);

    void doneLoading();
  }

  interface Presenter extends BasePresenter {
    void fetchRestaurantList(String searchText, String sortBy);

    void fetchRestaurantByLocation(String location);
  }
}