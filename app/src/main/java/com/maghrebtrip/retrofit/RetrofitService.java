package com.maghrebtrip.retrofit;

import com.google.gson.Gson;

import java.util.Properties;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    Retrofit retrofit;

    Properties properties = new Properties();
    String host = properties.getProperty("HOST", "http://10.1.6.9");

    public RetrofitService(int port) {
        initializeRetrofit(port);
    }

    public void initializeRetrofit(int port) {
        String baseUrl = host + ":" + port;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient.build())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
