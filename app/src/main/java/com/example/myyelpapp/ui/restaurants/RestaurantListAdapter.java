package com.example.myyelpapp.ui.restaurants;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.myyelpapp.data.models.Restaurant;
import com.example.myyelpapp.R;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
  private ArrayList<Restaurant> mRestaurantList;

  public RestaurantListAdapter(ArrayList<Restaurant> restaurantList) {
    mRestaurantList = restaurantList;
  }

  @Override public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.restaurant_item_view_holder, parent, false);
    RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(view);
    return restaurantViewHolder;
  }

  @Override public void onBindViewHolder(RestaurantViewHolder holder, int position) {
    ((RestaurantViewHolder) holder).bind(mRestaurantList.get(position));
  }

  @Override public int getItemCount() {
    return mRestaurantList.size();
  }
}

