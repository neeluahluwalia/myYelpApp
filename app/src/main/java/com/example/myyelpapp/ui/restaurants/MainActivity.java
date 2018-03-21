package com.example.myyelpapp.ui.restaurants;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import com.example.myyelpapp.R;
import com.example.myyelpapp.Utils;
import com.example.myyelpapp.data.models.Restaurant;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RestaurantListingContract.View {
  @BindView(R.id.restaurantView) RecyclerView mRestaurantView;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;
  @BindView(R.id.resultTitle) TextView resultTitle;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  NavigationView mNvDrawer;
  DrawerLayout mDrawer;
  private Menu mMenu;

  private RecyclerView.Adapter mAdapter;

  private ArrayList<Restaurant> mRestaurantList;

  private Context mContext;

  private RestaurantListingContract.Presenter mPresenter;

  private static String sortBy, searchString;
  SearchView searchView;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    searchString = getResources().getString(R.string.default_search_string);
    sortBy = getString(R.string.best_match);
    mContext = this;
    initView();

    if (Utils.isNetworkAvailable(this)) {
      mPresenter.fetchRestaurantList(searchString, sortBy);
    } else {
      Toast.makeText(mContext, getString(R.string.no_network_connection), Toast.LENGTH_LONG).show();
    }
  }

  private void initView() {
    mNvDrawer = (NavigationView) findViewById(R.id.nav_view);
    mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    mPresenter = new RestaurantListingPresenter(this);
    mRestaurantView.setHasFixedSize(true);
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    mRestaurantView.setLayoutManager(linearLayoutManager);
    mRestaurantList = new ArrayList<Restaurant>();
    mAdapter = new RestaurantListAdapter(mRestaurantList);
    mRestaurantView.setAdapter(mAdapter);
    showLoading();
    updateTitle();
    setSupportActionBar(mToolbar);
    final ActionBar ab = getSupportActionBar();
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setElevation(30);
    ab.setHomeButtonEnabled(true);
    ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
    mNvDrawer.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

    mNvDrawer.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
            selectDrawerItem(menuItem);
            return true;
          }
        });
  }

  public void selectDrawerItem(MenuItem menuItem) {
    if (menuItem.isChecked()) {
      menuItem.setChecked(false);
    } else {
      menuItem.setChecked(true);
    }
    switch (menuItem.getItemId()) {
      case R.id.searchByLocation:
        mDrawer.closeDrawers();
        Intent searchByLocationIntent = new Intent(this, SearchByLocation.class);
        startActivity(searchByLocationIntent);
        break;
    }
    mDrawer.closeDrawers();
  }

  @Override public boolean onCreateOptionsMenu(final Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    mMenu = menu;
    searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        searchString = query;
        updateTitle();
        searchView.setIconified(true);
        searchView.clearFocus();
        if (mMenu != null) {
          (mMenu.findItem(R.id.menu_search)).collapseActionView();
        }
        refreshList(query, sortBy);
        return false;
      }

      @Override public boolean onQueryTextChange(String query) {
        return true;
      }
    });

    return true;
  }

  private void updateTitle() {
    resultTitle.setText(String.format(getString(R.string.show_results), searchString));
  }

  private void refreshList(String query, String sortByt) {
    showLoading();
    if (Utils.isNetworkAvailable(this)) {
      mPresenter.fetchRestaurantList(query, sortByt);
    } else {
      Toast.makeText(mContext, getString(R.string.no_network_connection), Toast.LENGTH_LONG).show();
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        mDrawer.openDrawer(GravityCompat.START);
        return true;
      case R.id.bestMatchSort:
        sortBy = getString(R.string.best_match);
        refreshList(searchString, sortBy);
        break;
      case R.id.ratingSort:
        sortBy = getString(R.string.rating).toLowerCase();
        refreshList(searchString, sortBy);
        break;
      // as we are not fetching longitude and latitude values from user location for now
      //case R.id.distanceSort:
      //  sortBy = getResources().getString(R.string.distance);
      //  refreshList(location, sortBy);
      //  break;
      case R.id.popularSort:
        sortBy = getString(R.string.review_count);
        refreshList(searchString, sortBy);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void showRestaurantList(ArrayList<Restaurant> listRestaurants) {
    doneLoading();
    mRestaurantList.clear();
    mRestaurantList.addAll(listRestaurants);
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
}
