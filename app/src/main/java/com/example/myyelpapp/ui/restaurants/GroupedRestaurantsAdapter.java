package com.example.myyelpapp.ui.restaurants;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myyelpapp.R;
import com.example.myyelpapp.data.models.BaseModel;
import com.example.myyelpapp.data.models.Category;
import com.example.myyelpapp.data.models.Restaurant;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class GroupedRestaurantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private ArrayList<BaseModel> itemList = new ArrayList<>();

  public GroupedRestaurantsAdapter(ArrayList<BaseModel> itemList) {
    this.itemList = itemList;
  }

  @Override public int getItemViewType(int position) {
    /* also can change by checking model is instanceOf();
    BaseModel baseModel = ((BaseModel) combinedList.get(position));
    int typeOfItem;
    if(baseModel instanceof Restaurant){
      typeOfItem = 1;
    }else{
      typeOfItem = 0;
    }
    return typeOfItem;
    */
    return itemList.get(position).getType();
  }

  @Override public int getItemCount() {
    return itemList != null ? itemList.size() : 0;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    RecyclerView.ViewHolder viewHolder = null;
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    switch (viewType) {
      case BaseModel.TYPE_CATEGORY:
        View v1 = inflater.inflate(R.layout.category_title_item_view_holder, parent, false);
        viewHolder = new CategoryTitleViewHolder(v1);
        break;

      case BaseModel.TYPE_RESTAURANT:
        View v2 = inflater.inflate(R.layout.restaurant_item_view_holder, parent, false);
        viewHolder = new RestaurantViewHolder(v2);
        break;
    }

    return viewHolder;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    switch (viewHolder.getItemViewType()) {

      case BaseModel.TYPE_CATEGORY:
        Category category = (Category) itemList.get(position);
        CategoryTitleViewHolder categoryViewHolder = (CategoryTitleViewHolder) viewHolder;
        categoryViewHolder.bind(category);
        break;
      case BaseModel.TYPE_RESTAURANT:
        Restaurant restaurant = (Restaurant) itemList.get(position);
        RestaurantViewHolder restaurantViewHolder = (RestaurantViewHolder) viewHolder;
        restaurantViewHolder.bind(restaurant);
        break;
    }
  }
}

