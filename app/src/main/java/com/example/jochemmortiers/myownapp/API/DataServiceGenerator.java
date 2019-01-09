package com.example.jochemmortiers.myownapp.API;

import com.example.jochemmortiers.myownapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServiceGenerator {

    // creates a service which gets the data
    public static <S> S createService(Class<S> serviceClass) {
        final String BASE_URL = "http://unitypuzzlegame.com/";
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);

    }

}
