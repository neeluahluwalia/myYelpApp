package com.example.myyelpapp.data.models;

import java.io.Serializable;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class User implements Serializable {
  private String image_url;
  private String name;

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
}
