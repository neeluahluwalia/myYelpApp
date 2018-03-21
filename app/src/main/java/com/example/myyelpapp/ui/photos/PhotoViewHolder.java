package com.example.myyelpapp.ui.photos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.myyelpapp.R;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.photoView) ImageView photoView;

  private Context mContext;
  private int mPosition;

  public PhotoViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    this.mContext = itemView.getContext();
  }

  public void bind(String model) {
    bind(model, getAdapterPosition());
  }

  public void bind(String model, int position) {
    photoView.getLayoutParams().width = photoView.getLayoutParams().height =
        mContext.getResources().getDisplayMetrics().widthPixels / 2;
    Glide.with(mContext).load(model).into(photoView);
  }
}
