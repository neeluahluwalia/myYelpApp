package com.example.myyelpapp.ui.reviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.myyelpapp.Config;
import com.example.myyelpapp.R;
import com.example.myyelpapp.RetrofitClient;
import com.example.myyelpapp.Utils;
import com.example.myyelpapp.data.models.Review;
import com.example.myyelpapp.data.rest.YelpAPI;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class ReviewListingFragment extends Fragment implements ReviewListingContract.View {
  private AppCompatActivity mActivity;
  private RecyclerView.Adapter mAdapter;

  private YelpAPI yelpAPI;

  private ArrayList<Review> mReviewList;
  ReviewListingPresenter mPresenter;
  @BindView(R.id.reviewListing) RecyclerView mReviewListingView;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivity = (AppCompatActivity) getActivity();
    yelpAPI = RetrofitClient.getClient().create(YelpAPI.class);
    mPresenter = new ReviewListingPresenter(this);
    if (getArguments() != null) {
      Bundle bs = new Bundle();
      bs = getArguments();
      String id = bs.getString(Config.RESTAURANT_ID);
      if (Utils.isNetworkAvailable(mActivity)) {
        mPresenter.fetchReviewList(id);
      } else {
        Toast.makeText(mActivity, getString(R.string.no_network_connection), Toast.LENGTH_LONG)
            .show();
      }
    } else {
      Toast.makeText(mActivity, "Something went wrong", Toast.LENGTH_LONG).show();
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from(container.getContext())
        .inflate(R.layout.fragment_review_listing, container, false);
    ButterKnife.bind(this, view);
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
    mReviewListingView.setLayoutManager(linearLayoutManager);
    mReviewListingView.setHasFixedSize(true);
    mReviewList = new ArrayList<Review>();
    mAdapter = new ReviewListAdapter(mReviewList);
    mReviewListingView.setAdapter(mAdapter);
    return view;
  }

  @Override public void showReviewList(ArrayList<Review> listReviews) {
    mReviewList.addAll(listReviews);
    mAdapter.notifyDataSetChanged();
  }

  @Override public void showError(String message) {
    // we can also use dialog instead of toast
    Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
  }
}
