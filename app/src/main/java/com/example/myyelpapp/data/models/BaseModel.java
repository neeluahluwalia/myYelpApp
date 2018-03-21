package com.example.myyelpapp.data.models;

import java.io.Serializable;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public abstract class BaseModel {

  public static final int TYPE_CATEGORY = 0;
  public static final int TYPE_RESTAURANT = 1;

  abstract public int getType();
}