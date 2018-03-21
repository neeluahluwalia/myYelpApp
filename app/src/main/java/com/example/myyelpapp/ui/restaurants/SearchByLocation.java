package com.example.myyelpapp.ui.restaurants;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.myyelpapp.Config;
import com.example.myyelpapp.R;
import com.example.myyelpapp.Utils;
import com.example.myyelpapp.data.models.BaseModel;
import com.example.myyelpapp.data.models.Category;
import com.example.myyelpapp.data.models.Restaurant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by neeluahluwalia on 2018-03-18.
 * Activity which lets you search restaurants on the basis of location
 */

public class SearchByLocation extends AppCompatActivity implements RestaurantListingContract.View {
  @BindView(R.id.restaurantView) RecyclerView mRestaurantView;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;

  private GroupedRestaurantsAdapter mAdapter;

  private ArrayList<BaseModel> mRestaurantList;

  private Context mContext;

  private RestaurantListingContract.Presenter mPresenter;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.resultTitle) TextView mResultTitle;
  private static String location;
  private Menu mMenu;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mContext = this;
    // init view
    initView();
  }

  private void initView() {
    mRestaurantView.setHasFixedSize(true);
    final LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
    mRestaurantView.setLayoutManager(gridLayoutManager);
    mRestaurantList = new ArrayList<BaseModel>();
    mAdapter = new GroupedRestaurantsAdapter(mRestaurantList);
    mRestaurantView.setAdapter(mAdapter);
    showLoading();
    // default location
    location = Config.DEFAULT_LOCATION;
    mPresenter = new RestaurantListingPresenter(this);
    if (Utils.isNetworkAvailable(this)) {
      mPresenter.fetchRestaurantByLocation(location);
    } else {
      Toast.makeText(this, getString(R.string.no_network_connection), Toast.LENGTH_LONG).show();
    }
    updateTitle();
    setSupportActionBar(mToolbar);
    final ActionBar ab = getSupportActionBar();
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setElevation(30);
    ab.setHomeButtonEnabled(true);
  }

  private void updateTitle() {
    mResultTitle.setText(String.format(getString(R.string.show_location_results), location));
  }

  @Override public boolean onCreateOptionsMenu(final Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_location, menu);
    mMenu = menu;
    final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
    searchView.setQueryHint(getString(R.string.search_by_location));
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        location = query;
        updateTitle();
        refreshList(query);
        searchView.setIconified(true);
        searchView.clearFocus();
        if (mMenu != null) {
          (mMenu.findItem(R.id.menu_search)).collapseActionView();
        }
        return false;
      }

      @Override public boolean onQueryTextChange(String query) {
        return true;
      }
    });

    return true;
  }

  private void refreshList(String query) {
    showLoading();
    if (Utils.isNetworkAvailable(this)) {
      mPresenter.fetchRestaurantByLocation(query);
    } else {
      Toast.makeText(this, getString(R.string.no_network_connection), Toast.LENGTH_LONG).show();
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void showRestaurantList(ArrayList<Restaurant> listRestaurants) {
    doneLoading();
    mRestaurantList.clear();
    mRestaurantList.addAll(getListingByCategories(listRestaurants));
    mAdapter.notifyDataSetChanged();
    if (mRestaurantList.size() > 0) {
      mRestaurantView.scrollToPosition(0);
    }
  }

  @Override public void showLoading() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void showError(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
  }

  @Override public void doneLoading() {
    mProgressBar.setVisibility(View.GONE);
  }

  /* if a restaurant has two categories : showing that restaurant in both the category groups
  A Hashmap is created with key : category and value: list of restaurants which are of same category looping through the restaurants and their categories,
   adding <category,  List of restautants> if key(category) doesn't exist
   if already in map then add restaurant at that key(category)
  */
  public ArrayList<BaseModel> getListingByCategories(ArrayList<Restaurant> restaurantList) {
    HashMap<String, ArrayList<Restaurant>> restaurantsByCategoryHashMap =
        new HashMap<String, ArrayList<Restaurant>>();
    for (Restaurant restaurant : restaurantList) {
      for (Category category : restaurant.getCategories()) {
        if (!restaurantsByCategoryHashMap.containsKey(category.getTitle())) {
          ArrayList<Restaurant> arrayList = new ArrayList<>();
          arrayList.add(restaurant);
          restaurantsByCategoryHashMap.put(category.getTitle(), arrayList);
        } else {
          restaurantsByCategoryHashMap.get(category.getTitle()).add(restaurant);
        }
      }
    }
    ArrayList<BaseModel> listOfCategoriesAndRestaurants = new ArrayList<>();

    for (String title : restaurantsByCategoryHashMap.keySet()) {
      Category category = new Category();
      category.setTitle(title);
      category.setCount(restaurantsByCategoryHashMap.get(title).size());
      listOfCategoriesAndRestaurants.add(category);

      for (Restaurant restaurant : restaurantsByCategoryHashMap.get(title)) {
        listOfCategoriesAndRestaurants.add(restaurant);
      }
    }
    return listOfCategoriesAndRestaurants;
  }
}

