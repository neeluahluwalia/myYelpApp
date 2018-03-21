package com.example.myyelpapp.data.models;

import java.io.Serializable;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class Category extends BaseModel implements Serializable {
  private String alias;
  private String title;
  private int count;

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override public int getType() {
    return TYPE_CATEGORY;
  }
}
