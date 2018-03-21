package com.example.myyelpapp.ui.restaurants;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.myyelpapp.Config;
import com.example.myyelpapp.data.models.Restaurant;
import com.example.myyelpapp.R;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  @BindView(R.id.restaurantName) TextView restaurantName;

  @BindView(R.id.restaurantImage) ImageView restaurantImage;

  @BindView(R.id.reviewsCount) TextView reviewsCount;

  @BindView(R.id.categoryTitle) TextView categoryTitle;

  @BindView(R.id.rating) TextView rating;

  private Context mContext;

  private String mResId;

  public RestaurantViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    this.mContext = itemView.getContext();
    itemView.setOnClickListener(this);
  }

  public void bind(Restaurant model) {
    bind(model, getAdapterPosition());
  }

  public void bind(Restaurant model, int position) {
    mResId = model.getId();
    restaurantName.setText(model.getName());
    rating.setText(String.valueOf(model.getRating()));
    Glide.with(mContext).load(model.getImageUrl()).into(restaurantImage);
    reviewsCount.setText(String.valueOf(model.getReviewCount()) + " Reviews");
    categoryTitle.setText(model.getCategoryString());
  }

  @Override public void onClick(View v) {
    Intent restaurantDetailIntent = new Intent(mContext, RestaurantDetailActivity.class);
    restaurantDetailIntent.putExtra(Config.RESTAURANT_ID, mResId);
    mContext.startActivity(restaurantDetailIntent);
  }
}
