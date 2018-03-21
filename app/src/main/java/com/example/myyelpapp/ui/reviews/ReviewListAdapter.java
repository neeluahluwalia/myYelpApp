package com.example.myyelpapp.ui.reviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.myyelpapp.R;
import com.example.myyelpapp.data.models.Review;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
  private ArrayList<Review> mReviewList;

  public ReviewListAdapter(ArrayList<Review> reviewList) {
    mReviewList = reviewList;
  }

  @Override public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.review_item_view_holder, parent, false);
    ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
    return reviewViewHolder;
  }

  @Override public void onBindViewHolder(ReviewViewHolder holder, int position) {
    ((ReviewViewHolder) holder).bind(mReviewList.get(position));
  }

  @Override public int getItemCount() {
    return mReviewList.size();
  }
}

