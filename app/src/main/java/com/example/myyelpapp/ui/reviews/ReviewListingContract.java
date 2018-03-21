package com.example.myyelpapp.ui.reviews;

import com.example.myyelpapp.ui.base.BasePresenter;
import com.example.myyelpapp.ui.base.BaseView;
import com.example.myyelpapp.data.models.Review;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public interface ReviewListingContract {
  interface View extends BaseView<ReviewListingContract.Presenter> {
    void showReviewList(ArrayList<Review> listReviews);

    void showError(String message);
  }

  interface Presenter extends BasePresenter {
    void fetchReviewList(String restaurantId);
  }
}
