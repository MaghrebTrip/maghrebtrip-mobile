package com.maghrebtrip.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    Retrofit retrofit;
    int port = 8080;

    public RetrofitService(int port) {
        initializeRetrofit(port);
    }

    public void initializeRetrofit(int port) {
        // to get the IP address type: ipconfig and search for your IPv4
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:" + port)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
