package com.example.myyelpapp.ui.reviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.myyelpapp.R;
import com.example.myyelpapp.data.models.Review;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.userName) TextView userName;

  @BindView(R.id.userImage) ImageView userImage;

  @BindView(R.id.reviewText) TextView reviewText;

  private Context mContext;

  private Review mReview;

  private int mPosition;

  public ReviewViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    this.mContext = itemView.getContext();
  }

  public void bind(Review model) {
    bind(model, getAdapterPosition());
  }

  public void bind(Review model, int position) {
    mReview = model;
    userName.setText(
        Html.fromHtml(model.getUser().getName() + "<b> rated </b>" + model.getRating()));
    Glide.with(mContext).load(model.getUser().getImageUrl()).into(userImage);
    reviewText.setText(model.getText());
  }
}
