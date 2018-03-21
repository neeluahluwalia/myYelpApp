package com.example.myyelpapp.ui.restaurants;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.myyelpapp.Config;
import com.example.myyelpapp.R;
import com.example.myyelpapp.Utils;
import com.example.myyelpapp.data.models.Restaurant;
import com.example.myyelpapp.ui.photos.PhotoListingFragment;
import com.example.myyelpapp.ui.reviews.ReviewListingFragment;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class RestaurantDetailActivity extends AppCompatActivity
    implements RestaurantDetailContract.View {
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.mainImage) ImageView mImage;
  @BindView(R.id.name) TextView mName;
  @BindView(R.id.address) TextView mAddress;
  @BindView(R.id.rating) TextView mRating;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;
  private RestaurantDetailPresenter mPresenter;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_restaurant_detail);
    ButterKnife.bind(this);
    mPresenter = new RestaurantDetailPresenter(this);
    String resId = getIntent().getStringExtra(Config.RESTAURANT_ID);
    if (resId != null && Utils.isNetworkAvailable(this)) {
      mPresenter.fetchRestaurantDetail(resId);
    }
    ReviewListingFragment reviewListingFragment = new ReviewListingFragment();
    Bundle b = new Bundle();
    b.putString(Config.RESTAURANT_ID, resId);
    reviewListingFragment.setArguments(b);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.mainFrame, reviewListingFragment, "1")
        .commit();
    setSupportActionBar(mToolbar);
    final ActionBar ab = getSupportActionBar();
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setElevation(30);
    ab.setHomeButtonEnabled(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void showDetails(Restaurant restaurant) {
    mName.setText(restaurant.getName());
    mAddress.setText(restaurant.getLocation().getCompleteAddress());
    mRating.setText(String.valueOf(restaurant.getRating()));
    Glide.with(this).load(restaurant.getImageUrl()).into(mImage);
    PhotoListingFragment photosFragment = new PhotoListingFragment();
    ArrayList<String> photoList = new ArrayList<>();
    photoList.addAll(restaurant.getPhotos());
    Bundle bs = new Bundle();
    bs.putStringArrayList(Config.RESTAURANT_PHOTOS, photoList);
    photosFragment.setArguments(bs);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.photosFrame, photosFragment, "1")
        .commit();
  }

  @Override public void showLoading() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override public void doneLoading() {
    mProgressBar.setVisibility(View.GONE);
  }
}
