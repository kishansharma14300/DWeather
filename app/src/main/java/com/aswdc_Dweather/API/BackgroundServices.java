package com.aswdc_Dweather.API;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.aswdc_Dweather.Activity.LikedCityListActivity;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ResponseOfYahhoWeatherApi;

import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;

public class BackgroundServices extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
   public static List<String>databaseCities;
    public BackgroundServices(String name) {
        super("backGroundServieces");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int k=1;
        for (int i=0;i<databaseCities.size();i++){
            callYahhooApi(databaseCities.get(i));
        }
        Log.e("kishan===","success");
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    private void callYahhooApi(String s) {
        Call<ResponseOfYahhoWeatherApi> call=ApiYahhoApiClient.getClient().create(ApiInterface.class).getYahhoWeatherResponseByCityName(s,"json","c");
        try {
            LikedCityListActivity.listItems.add(call.execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
