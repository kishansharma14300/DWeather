package com.aswdc_Dweather.API;

import com.aswdc_Dweather.Beans.currentData.ResponseOfLattitudeAndLongitudeApi;
import com.aswdc_Dweather.POJOS.Accuwether_location.ResponseOfAutoCompleteTextViewFromAccuWeather;
import com.aswdc_Dweather.POJOS.Forcast.ResponseForcast;
import com.aswdc_Dweather.POJOS.only_temp.NewResponse;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ResponseOfYahhoWeatherApi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


//    @GET("weather")
//    Call<ResponseOfNormalApi> getWeatherDetailsInCelcius(
//            @Query("q") String cityName,
//            @Query("appid") String apiKey,
//            @Query("units") String metric);


    //http://api.openweathermap.org/data/2.5/weather?lat=22.3039&lon=70.8022&appid=6a79fe478568e5160fe26e7a53dd4fa7
    @GET("weather")
    Call<ResponseOfLattitudeAndLongitudeApi> getCurrentDataByLatLong(
            @Query("lat") String latitude,
            @Query("lon") String longetude,
            @Query("appid") String appId);

    @GET("weather")
    Call<ResponseOfLattitudeAndLongitudeApi> getCurrentDataByCityName(
            @Query("q") String cityName,
            @Query("appid") String appId);


    //http://api.openweathermap.org/data/2.5/forecast?q=rajkot,in&appid=6a79fe478568e5160fe26e7a53dd4fa7
    @GET("forecast")
    Call<ResponseForcast> getForcastDetailsInCelcius(
            @Query("q ") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String metric);

//    http://dataservice.accuweather.com/locations/v1/cities/autocomplete?q=r&apikey=L0iSpHqoFPLuTvcpLjJqUua7vq2U7T3C
    @GET("locations/v1/cities/autocomplete")
    Call<List<ResponseOfAutoCompleteTextViewFromAccuWeather>> getLocations(
            @Query("q") String text,
            @Query("apikey") String apikey

    );


    @GET("forecasts/{cityId}")
    Call<NewResponse> getTodayForcastDetails(
            @Path("cityId") String cityName,
            @Query("format") String format,
            @Query("filter") String filter,
            @Query("limit") String limit,
            @Query("client_id") String clientID,
            @Query("client_secret") String clientSecret);

//     https://weather-ydn-yql.media.yahoo.com/forecastrss?lat=37.372&lon=-122.038
    @POST("forecastrss")
    Call<ResponseOfYahhoWeatherApi> getYahhoWeatherResponseByLatitudeAndLongatitude(
            @Query("lat") String latitude,
            @Query("lon") String longatude,
            @Query("format") String format,
            @Query("u") String unit);


    //    https://weather-ydn-yql.media.yahoo.com/forecastrss?location=sunnyvale,ca
//https://weather-ydn-yql.media.yahoo.com/forecastrss?location=sunnyvale
    @POST("forecastrss")
    Call<ResponseOfYahhoWeatherApi> getYahhoWeatherResponseByCityName(
            @Query("location") String location,
            @Query("format") String format,
            @Query("u") String unit);


}


//for today's date condition api


