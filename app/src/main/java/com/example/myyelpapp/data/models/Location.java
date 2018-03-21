package com.example.myyelpapp.data.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by neeluahluwalia on 2018-03-19.
 */

public class Location implements Serializable {
  private String address1;
  private String address2;
  private String address3;
  private String zip_code;
  private String city;
  @SerializedName("display_address") private List<String> displayAddress = null;

  public String getCompleteAddress() {
    String address = "";
    for (String s : displayAddress) {
      address = address + s + " ";
    }
    return address;
  }

  public List<String> getDisplayAddress() {
    return displayAddress;
  }

  public void setDisplayAddress(List<String> displayAddress) {
    this.displayAddress = displayAddress;
  }
}

