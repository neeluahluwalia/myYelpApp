package com.example.myyelpapp.ui.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.myyelpapp.R;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class PhotoListingAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
  private ArrayList<String> mPhotoList;

  public PhotoListingAdapter(ArrayList<String> photoList) {
    mPhotoList = photoList;
  }

  @Override public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.photo_item_view_holder, parent, false);
    PhotoViewHolder photoViewHolder = new PhotoViewHolder(view);
    return photoViewHolder;
  }

  @Override public void onBindViewHolder(PhotoViewHolder holder, int position) {
    ((PhotoViewHolder) holder).bind(mPhotoList.get(position));
  }

  @Override public int getItemCount() {
    return mPhotoList.size();
  }
}
