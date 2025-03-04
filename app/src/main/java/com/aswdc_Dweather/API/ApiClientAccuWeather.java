package com.aswdc_Dweather.API;

import com.aswdc_Dweather.utility.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientAccuWeather {
    public static final String BASE_URL = "http://dataservice.accuweather.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
