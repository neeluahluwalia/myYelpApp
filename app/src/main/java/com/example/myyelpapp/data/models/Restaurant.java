package com.example.myyelpapp.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class Restaurant extends BaseModel implements Serializable {
  private String id;
  private String name;
  private String image_url;
  private int review_count;
  private float rating;
  private String phone;
  private ArrayList<Category> categories;
  private Location location;
  private List<String> photos = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageUrl() {
    return image_url;
  }

  public void setImageUrl(String image_url) {
    this.image_url = image_url;
  }

  public int getReviewCount() {
    return review_count;
  }

  public void setReviewCount(int review_count) {
    this.review_count = review_count;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public ArrayList<Category> getCategories() {
    return categories;
  }

  public void setCategories(ArrayList<Category> categories) {
    this.categories = categories;
  }

  public String getCategoryString(){
    String categoryString= "";
    if(this.categories != null) {
      for (int i = 0; i < this.categories.size(); i++) {
        categoryString = categoryString + categories.get(i).getTitle();
        if (i < this.categories.size() - 1) {
          categoryString = categoryString + ", ";
        }
      }
    }
    return categoryString;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public List<String> getPhotos() {
    return photos;
  }

  public void setPhotos(List<String> photos) {
    this.photos = photos;
  }
  
  @Override public int getType() {
    return TYPE_RESTAURANT;
  }
}
