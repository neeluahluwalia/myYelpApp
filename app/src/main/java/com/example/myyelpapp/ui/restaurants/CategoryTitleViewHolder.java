package com.example.myyelpapp.ui.restaurants;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.myyelpapp.R;
import com.example.myyelpapp.data.models.Category;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class CategoryTitleViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.categoryTitle) TextView categoryTitle;

  private Context mContext;

  public CategoryTitleViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    this.mContext = itemView.getContext();
  }

  public void bind(Category model) {
    bind(model, getAdapterPosition());
  }

  public void bind(Category model, int position) {
    categoryTitle.setText(model.getTitle() + "(" + model.getCount() + ")");
  }
}
