package com.example.myyelpapp;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neeluahluwalia on 2018-03-18.
 */

public class RetrofitClient {

  private static Retrofit retrofit = null;

  public static Retrofit getClient() {

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(new Interceptor() {
      @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        // considering Api_key token has not expired : if expired then based on the response code we should call API to get api_key and update config
        Request request = original.newBuilder()
            .header("Authorization", Config.API_KEY)
            .method(original.method(), original.body())
            .build();

        return chain.proceed(request);
      }
    });

    retrofit = new Retrofit.Builder().baseUrl(Config.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build();

    return retrofit;
  }
}