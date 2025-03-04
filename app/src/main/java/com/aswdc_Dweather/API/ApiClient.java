package com.aswdc_Dweather.API;

import com.aswdc_Dweather.utility.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(Constants.CONSUMER_KEY)
                .consumerSecret(Constants.CONSUMER_SECRET)
                .build();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(oauth1Woocommerce)// Interceptor oauth1Woocommerce added
                .build();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
