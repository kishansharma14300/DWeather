package com.aswdc_Dweather.Activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.aswdc_Dweather.API.ApiInterface;
import com.aswdc_Dweather.API.ApiYahhoApiClient;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ResponseOfYahhoWeatherApi;
import com.aswdc_Dweather.R;
import com.aswdc_Dweather.adapter.SearchedCityAdapter;
import com.aswdc_Dweather.dbHelper.DBHelper_City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class LikedCityListActivity extends AppCompatActivity {

    Button btnAddCity;
  public static   List<ResponseOfYahhoWeatherApi> listItems = new ArrayList<>();
    private RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    int i;
    public DBHelper_City dbCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_city_list);
        init();
        List<String> ci=dbCity.getLikedCity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        listItems.clear();
        if (dbCity.getLikedCity().size() != 0) {
            for (i = 0; i < dbCity.getLikedCity().size(); i++) {
                callYahhooApi(dbCity.getLikedCity().get(i));
            }
            displayList();

        }


    }

    private void displayList() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new SearchedCityAdapter(listItems, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    private void callYahhooApi(String cityName) {
        Call<ResponseOfYahhoWeatherApi> yahhoWeatherApiCal =
                ApiYahhoApiClient.getClient().create(ApiInterface.class).getYahhoWeatherResponseByCityName(cityName, "json", "c");
//
//        yahhoWeatherApiCal.enqueue(new Callback<ResponseOfYahhoWeatherApi>() {
//            @Override
//            public void onResponse(Call<ResponseOfYahhoWeatherApi> call, Response<ResponseOfYahhoWeatherApi> response) {
//                listItems.add(response.body());
////                if (dbCity.getLikedCity().size() == (i + 1))
////                    displayList();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseOfYahhoWeatherApi> call, Throwable t) {
//                Toast.makeText(LikedCityListActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        });
        try {
            Response<ResponseOfYahhoWeatherApi> weatherApi=yahhoWeatherApiCal.execute();

            listItems.add(weatherApi.body());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void init() {
       // `\7// ` `x = findViewById(R.id.btn_addCity_mainAcitivty);
        recyclerView = (RecyclerView) findViewById(R.id.rv_LikedCityActivity);
        dbCity=new DBHelper_City(LikedCityListActivity.this);
    }
}
