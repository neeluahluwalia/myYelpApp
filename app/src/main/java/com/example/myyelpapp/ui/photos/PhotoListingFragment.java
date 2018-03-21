package com.example.myyelpapp.ui.photos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class PhotoListingFragment extends Fragment {
  @BindView(R.id.photoListingView) RecyclerView photoListingView;

  private AppCompatActivity mActivity;
  private PhotoListingAdapter mAdapter;
  private ArrayList<String> mPhotoList;

  public PhotoListingFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    mActivity = (AppCompatActivity) getActivity();
    mPhotoList = new ArrayList<>();
    if (getArguments() != null) {
      Bundle bs = new Bundle();
      bs = getArguments();
      mPhotoList = bs.getStringArrayList(Config.RESTAURANT_PHOTOS);
    } else {
      Toast.makeText(mActivity, "No Photos", Toast.LENGTH_LONG).show();
    }
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = LayoutInflater.from(container.getContext())
        .inflate(R.layout.fragment_photo_listing, container, false);
    ButterKnife.bind(this, view);
    final GridLayoutManager gridLayoutManager =
        new GridLayoutManager(mActivity, 2, LinearLayoutManager.VERTICAL, false);
    photoListingView.setLayoutManager(gridLayoutManager);
    photoListingView.setHasFixedSize(true);
    mAdapter = new PhotoListingAdapter(mPhotoList);
    photoListingView.setAdapter(mAdapter);
    return view;
  }
}
