package com.example.myyelpapp.data.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class BusinessResponseClass implements Serializable {

  private ArrayList<Restaurant> businesses;

  private ArrayList<Review> reviews;

  public ArrayList<Restaurant> getBusinesses() {
    return businesses;
  }

  public void setBusinesses(ArrayList<Restaurant> businesses) {
    this.businesses = businesses;
  }

  public ArrayList<Review> getReviews() {
    return reviews;
  }

  public void setReviews(ArrayList<Review> reviews) {
    this.reviews = reviews;
  }
}
