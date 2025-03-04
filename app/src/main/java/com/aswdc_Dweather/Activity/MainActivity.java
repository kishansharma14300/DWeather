package com.aswdc_Dweather.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aswdc_Dweather.API.ApiClient;
import com.aswdc_Dweather.API.ApiClientAccuWeather;
import com.aswdc_Dweather.API.ApiInterface;
import com.aswdc_Dweather.API.ApiYahhoApiClient;
import com.aswdc_Dweather.Beans.currentData.ResponseOfLattitudeAndLongitudeApi;
import com.aswdc_Dweather.POJOS.Accuwether_location.ResponseOfAutoCompleteTextViewFromAccuWeather;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.CurrentObservation;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ForecastsItem;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ResponseOfYahhoWeatherApi;
import com.aswdc_Dweather.R;
import com.aswdc_Dweather.adapter.FourDayForcastingAdapter;
import com.aswdc_Dweather.dbHelper.DBHelper_City;
import com.aswdc_Dweather.utility.CommonMethods;
import com.aswdc_Dweather.utility.Constants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.suke.widget.SwitchButton;
import com.varunest.sparkbutton.SparkButton;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aswdc_Dweather.utility.Constants.OPENWEATHER_API_KEY;


public class MainActivity extends AppCompatActivity implements LocationListener,NavigationView.OnNavigationItemSelectedListener  {
    TextView tvCurrentTemp, tvCurrentHigh, tvCurrentLow, tvChanceOfRain,tvCelcius,tvFernhit,
            tvHumidity, tvSunrise, tvSunset, tvWind, tvVisibility, tvPressure, tvClouds, tvConditon;
    public static TextView tvLoacation;
    ImageView ivImage,ivNavigation;
    String unit = "c";
    NavigationView navigationView;
    public static boolean searchItemClick = false;
    Button btnGps, btnLikedCity;
    private ResponseOfYahhoWeatherApi responseOfYahhoWeatherApi;
    public double latMain = 0, langMain = 0;
    public static double lat = 0, lan = 0;
    public DBHelper_City dbCity;
    public static SparkButton likeButton;
    LocationManager locationManager;
    android.location.Location location;
    RecyclerView recyclerView;
    int firstTimeLoadJsonData = 0;
    RecyclerView.Adapter mAdapter;
    private AutoCompleteTextView actv;
    public android.support.v4.widget.DrawerLayout drawerLayout;
    private GestureDetectorCompat gestureDetectorCompat = null;
    List<ForecastsItem> forecasts = new ArrayList<>();
    com.aswdc_Dweather.POJOS.yahho_weather_reponse.Location locationDetails;
    //    List<String>jsonIndiaStateName=new ArrayList<>();  for getting indianCityNames.json file
    List<String> cityNameList = new ArrayList<>();
    com.suke.widget.SwitchButton swCelciusFarenhit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        checkPermission();
        navigationView.setNavigationItemSelectedListener(this);
//        getCityNamesFromJsonData();
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.hideKeyboard(MainActivity.this);
                actv.setText("");
                callYahhoApi("gpsCall");
                getTodayWeatherDetailsFromOpenWeather("gpsCall");

            }
        });

        btnLikedCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);

                }
//                MainActivity.this.startActivity(new Intent(MainActivity.this, LikedCityListActivity.class));
            }
        });
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeButton.isChecked()) {
                    likeButton.setChecked(false);
                    dbCity.deleteLocation(locationDetails.getCity());
                } else {
                    likeButton.setChecked(true);
                    dbCity.insert(locationDetails);

                }
            }
        });
        swCelciusFarenhit.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    tvCelcius.setTypeface(tvCelcius.getTypeface(), Typeface.BOLD);
                    tvFernhit.setTypeface(tvFernhit.getTypeface(), Typeface.NORMAL);

                    unit = "c";
                    callYahhoApi("tempChange");
                } else {

                    tvCelcius.setTypeface(tvCelcius.getTypeface(), Typeface.NORMAL);
                    tvFernhit.setTypeface(tvFernhit.getTypeface(), Typeface.BOLD);
                    unit = "f";
                    callYahhoApi("tempChange");
                }
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
        actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (firstTimeLoadJsonData == 0)
                    callGetLocationApi();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (actv.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
                    } else {

                        performSearch(actv.getText().toString() + ",in");
                        actv.setText("");
                        CommonMethods.hideKeyboard(MainActivity.this);
                    }
                    return true;
                }
                return false;
            }


        });
    }



    private void callGetLocationApi() {
        String x = actv.getText().toString();
        Call<List<ResponseOfAutoCompleteTextViewFromAccuWeather>> accuWeatherCall = ApiClientAccuWeather.getClient().create(ApiInterface.class).getLocations(x, Constants.ACCUWEATHER_API_KEY);
        accuWeatherCall.enqueue(new Callback<List<ResponseOfAutoCompleteTextViewFromAccuWeather>>() {
            @Override
            public void onResponse(Call<List<ResponseOfAutoCompleteTextViewFromAccuWeather>> call, Response<List<ResponseOfAutoCompleteTextViewFromAccuWeather>> response) {
                try {
                    ResponseOfAutoCompleteTextViewFromAccuWeather item = response.body().get(0);
                    cityNameList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        item = response.body().get(i);
                        cityNameList.add(item.getLocalizedName() + ", " + item.getCountry().getID());
                    }
                    setAutoCompleteTextAdapter();
                } catch (Exception e) {
                    if (firstTimeLoadJsonData == 0)
                        getCityNamesFromJsonData();
                }

            }

            @Override
            public void onFailure(Call<List<ResponseOfAutoCompleteTextViewFromAccuWeather>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof AutoCompleteTextView) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    v.clearFocus();
                    CommonMethods.hideKeyboard(MainActivity.this);
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }


    private void performSearch(String s) {
        callYahhoApi(s);
        getTodayWeatherDetailsFromOpenWeather(s);
    }


    private void getCityNamesFromJsonData() {
        firstTimeLoadJsonData++;
//        jsonIndiaStateName.add("Andaman and Nicobar Islands");
//        jsonIndiaStateName.add("Andhra Pradesh");
//        jsonIndiaStateName.add("Arunachal Pradesh");
//        jsonIndiaStateName.add("Assam");
//        jsonIndiaStateName.add("Bihar");
//        jsonIndiaStateName.add("Chandigarh");
//        jsonIndiaStateName.add("Chhattisgarh");
//        jsonIndiaStateName.add("Dadra and Nagar Haveli");
//        jsonIndiaStateName.add("Delhi");
//        jsonIndiaStateName.add("Goa");
//        jsonIndiaStateName.add("Gujarat");
//        jsonIndiaStateName.add("Haryana");
//        jsonIndiaStateName.add("Himachal Pradesh");
//        jsonIndiaStateName.add("Jammu and Kashmir");
//        jsonIndiaStateName.add("Jharkhand");
//        jsonIndiaStateName.add("Karnataka");
//        jsonIndiaStateName.add("Karnatka");
//        jsonIndiaStateName.add("Kerala");
//        jsonIndiaStateName.add("Madhya Pradesh");
//        jsonIndiaStateName.add("Maharashtra");
//        jsonIndiaStateName.add("Manipur");
//        jsonIndiaStateName.add("Meghalaya");
//        jsonIndiaStateName.add("Mizoram");
//        jsonIndiaStateName.add("Nagaland");
//        jsonIndiaStateName.add("Odisha");
//        jsonIndiaStateName.add("Puducherry");
//        jsonIndiaStateName.add("Punjab");
//        jsonIndiaStateName.add("Rajasthan");
//        jsonIndiaStateName.add("Tamil Nadu");
//        jsonIndiaStateName.add("Telangana");
//        jsonIndiaStateName.add("Tripura");
//        jsonIndiaStateName.add("Telangana");

        //Get json Info to cityNameList
//        String json;
//        try {
//            InputStream is =getAssets().open("indian_state_city.json");
//            int size=is.available();
//            byte[] buffer=new byte[size];
//            is.read(buffer);
//            is.close();
//            json=new String(buffer,"UTF-8");
//            JSONObject mainJson=new JSONObject(json);
//            JSONArray jsonArray = new JSONArray();
//            for (int i = 0; i < jsonIndiaStateName.size(); i++){
//                JSONArray jsonElements=mainJson.getJSONArray(jsonIndiaStateName.get(i));
//                    for (int j=0;j<jsonElements.length();j++){
//                        jsonArray.put(jsonElements.getString(j));
////                        cityNameList.add(jsonElements.getString(j));
//                    }
//            }
//            String FILENAME = "indianCityNames.json";
//            String string = jsonArray.toString();
        String json;
        try {
            InputStream is = getAssets().open("indianCityNames.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                cityNameList.add(jsonArray.getString(i));
            }

            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(cityNameList);
            cityNameList.clear();
            cityNameList.addAll(hashSet);


            setAutoCompleteTextAdapter();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setAutoCompleteTextAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, cityNameList);
        actv.setAdapter(adapter);
        actv.setThreshold(1);
    }


    private void
    getTodayWeatherDetailsFromOpenWeather(String callType) {
        Call<ResponseOfLattitudeAndLongitudeApi> currentDateCall;
        if (callType.equals("gpsCall")) {
            currentDateCall = ApiClient.getClient().create(ApiInterface.class).getCurrentDataByLatLong(String.valueOf(latMain), String.valueOf(langMain), OPENWEATHER_API_KEY);
        } else if (callType.equals("itemClickCall")) {
            currentDateCall = ApiClient.getClient().create(ApiInterface.class).getCurrentDataByLatLong(String.valueOf(lat), String.valueOf(lan), OPENWEATHER_API_KEY);
        } else if (callType.equals("tempChange")) {
            currentDateCall =
                    ApiClient.getClient().create(ApiInterface.class).getCurrentDataByCityName(tvLoacation.getText().toString(), OPENWEATHER_API_KEY);
        } else {
            currentDateCall = ApiClient.getClient().create(ApiInterface.class).getCurrentDataByCityName(actv.getText().toString() + ",in", OPENWEATHER_API_KEY);
        }
        currentDateCall.enqueue(new Callback<ResponseOfLattitudeAndLongitudeApi>() {
            @Override
            public void onResponse(Call<ResponseOfLattitudeAndLongitudeApi> call, Response<ResponseOfLattitudeAndLongitudeApi> response) {

            try {
                setOpenWeatherConditonImage(response.body().getWeather().get(0).getIcon(), ivImage);
                setOpenWeatherConditonImage(response.body().getWeather().get(0).getIcon(), ivNavigation);

                tvConditon.setText(response.body().getWeather().get(0).getDescription());
                try {
                    tvChanceOfRain.setText(String.valueOf(response.body().getRain().getJsonMember3h() * 10) + "%");
                } catch (Exception e) {
                    tvChanceOfRain.setText("No Available");
                }
                try {
                    tvClouds.setText(String.valueOf(response.body().getClouds().getAll()));
                } catch (Exception e) {
                    tvClouds.setText("not Available");
                }
            }catch(Exception e){
                Toast.makeText(MainActivity.this, "City is not found", Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<ResponseOfLattitudeAndLongitudeApi> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void callYahhoApi(String callType) {

        Call<ResponseOfYahhoWeatherApi> yahhoWeatherApiCall;
        if (callType.equals("gpsCall")) {
            yahhoWeatherApiCall =
                    ApiYahhoApiClient.getClient().create(ApiInterface.class).getYahhoWeatherResponseByLatitudeAndLongatitude(String.valueOf(latMain), String.valueOf(langMain), "json", unit);
        } else if (callType.equals("itemClickCall")) {
            yahhoWeatherApiCall =
                    ApiYahhoApiClient.getClient().create(ApiInterface.class).getYahhoWeatherResponseByLatitudeAndLongatitude(String.valueOf(lat), String.valueOf(lan), "json", unit);
        } else if (callType.equals("tempChange")) {
            yahhoWeatherApiCall =
                    ApiYahhoApiClient.getClient().create(ApiInterface.class).getYahhoWeatherResponseByCityName(tvLoacation.getText().toString(), "json", unit);
        } else {
            yahhoWeatherApiCall =
                    ApiYahhoApiClient.getClient().create(ApiInterface.class).getYahhoWeatherResponseByCityName(actv.getText().toString(), "json", unit);
        }
        yahhoWeatherApiCall.enqueue(new Callback<ResponseOfYahhoWeatherApi>() {

            @Override
            public void onResponse(Call<ResponseOfYahhoWeatherApi> call, Response<ResponseOfYahhoWeatherApi> response) {

                String city = response.body().getLocation().getCity();
                if (!TextUtils.isEmpty(city)) {
                    responseOfYahhoWeatherApi = response.body();
                    forecasts.clear();

                    locationDetails = response.body().getLocation();
//                loadImage(ivMainWeatherIcon, responseOfYahhoWeatherApi.getCurrentObservation().getCondition().getText());

                    tvLoacation.setText(response.body().getLocation().getCity());
                    CurrentObservation currentObservation = response.body().getCurrentObservation();
                    List<String> cities = dbCity.getLikedCity();
                    if (cities.size() == 0) {
                        likeButton.setChecked(false);
                    } else {
                        for (int i = 0; i < cities.size(); i++) {
                            if (response.body().getLocation().getCity().equals(cities.get(i))) {
                                likeButton.setChecked(true);
                                break;
                            } else {
                                likeButton.setChecked(false);
                            }
                        }

                    }

                    if (swCelciusFarenhit.isChecked()) {


                        tvCurrentHigh.setText("↑"+String.valueOf(response.body().getForecasts().get(0).getHigh()) + Constants.CELSIUS);
                        tvCurrentLow.setText("↓"+String.valueOf(response.body().getForecasts().get(0).getLow()) + Constants.CELSIUS);
                        tvCurrentTemp.setText(responseOfYahhoWeatherApi.getCurrentObservation().getCondition().getTemperature() + Constants.CELSIUS);
                        tvWind.setText(currentObservation.getWind().getSpeed() + Constants.KM_H);
                        tvVisibility.setText(currentObservation.getAtmosphere().getVisibility() + Constants.KM);
                        tvPressure.setText(currentObservation.getAtmosphere().getPressure() + Constants.MBAR);
                    } else {

                        tvCurrentHigh.setText("↑"+String.valueOf(response.body().getForecasts().get(0).getHigh()) + Constants.FAHRENHEIT);
                        tvCurrentLow.setText("↓"+String.valueOf(response.body().getForecasts().get(0).getLow()) + Constants.FAHRENHEIT);
                        tvCurrentTemp.setText(responseOfYahhoWeatherApi.getCurrentObservation().getCondition().getTemperature() + Constants.FAHRENHEIT);
                        tvWind.setText(currentObservation.getWind().getSpeed() + Constants.MILE_H);
                        tvVisibility.setText(currentObservation.getAtmosphere().getVisibility() + Constants.MILE);
                        tvPressure.setText(currentObservation.getAtmosphere().getPressure() + Constants.INCH_HG);
                    }

                    tvHumidity.setText(String.valueOf(currentObservation.getAtmosphere().getHumidity()) + "%");
                    //tvConditon.setText(currentObservation.getCondition().getText());
                    tvSunrise.setText(currentObservation.getAstronomy().getSunrise());
                    tvSunset.setText(currentObservation.getAstronomy().getSunset());

//                    ivImage.setImageResource(getImageId(response.body().getCurrentObservation().getCondition().getCode()));

                    for (int i = 1; i < response.body().getForecasts().size(); i++)
                        forecasts.add(response.body().getForecasts().get(i));


                    displayList();
                } else {
                    Toast.makeText(MainActivity.this, "City is not found", Toast.LENGTH_SHORT).show();
                }
            }

            private void displayList() {
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);

                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new FourDayForcastingAdapter(forecasts, MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }


            @Override
            public void onFailure(Call<ResponseOfYahhoWeatherApi> call, Throwable t) {
            }
        });
    }



    private void setGradiantColor(int p) {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{p, p});
        gd.setCornerRadius(0f);

        drawerLayout.setBackground(gd);
    }

    private void checkPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
//                            getTodayWeatherDetails();

                            if (searchItemClick) {
                                searchItemClick = false;
                                callYahhoApi("itemClickCall");
                                getTodayWeatherDetailsFromOpenWeather("itemClickCall");
                            } else {
                                callYahhoApi("gpsCall");
                                getTodayWeatherDetailsFromOpenWeather("gpsCall");
                            }
                        }

                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }


                }).check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Location Permission");
        builder.setMessage("please give us location permission");
        builder.setPositiveButton("settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                MainActivity.this.openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public static void setOpenWeatherConditonImage(String icon, ImageView imageView) {

        if (icon.equals("01d")) {
            imageView.setImageResource(R.drawable.clear_sky);
//            setGradiantColor(R.color.clear_sky_background);
        }
        if (icon.equals("01n")) {
            imageView.setImageResource(R.drawable.night);

//            setGradiantColor(R.color.clear_night_background);
        }
        if (icon.equals("02d")) {
            imageView.setImageResource(R.drawable.sun_few_clouds);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("02n")) {
            imageView.setImageResource(R.drawable.night_with_cloud);

//            setGradiantColor(R.color.clear_night_background);
        }
        if (icon.equals("03d") || icon.equals("03n")) {
            imageView.setImageResource(R.drawable.clouds);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("04d") || icon.equals("04n")) {
            imageView.setImageResource(R.drawable.broken_clouds);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("09n") || icon.equals("09d")) {
            imageView.setImageResource(R.drawable.light_rain);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("10d")) {
            imageView.setImageResource(R.drawable.sun_with_rain);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("10n")) {
            imageView.setImageResource(R.drawable.night_with_rain);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("11d")||icon.equals("11n")) {
            imageView.setImageResource(R.drawable.rain_with_thundered);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("13d")||icon.equals("13n")) {
            imageView.setImageResource(R.drawable.snow);

//            setGradiantColor(R.color.cloudy_background);
        }
        if (icon.equals("50d")||icon.equals("50n")) {
            imageView.setImageResource(R.drawable.mist);

//            setGradiantColor(R.color.cloudy_background);
        }
    }

    public static int getImageId(int code) {
        switch (code) {
            case 0:
                return (R.drawable.tornado);

            case 1:
                return (R.drawable.tornado);
            case 2:
                return (R.drawable.tornado);
            case 3:
                return (R.drawable.severe_thunderstorms);
            case 4:
                return (R.drawable.severe_thunderstorms);
            case 5:
                return (R.drawable.rain_snow);
            case 6:
                return (R.drawable.rain_snow);
            case 7:
                return (R.drawable.rain_snow);
            case 8:
                return (R.drawable.mist);
            case 9:
                return (R.drawable.mist);
            case 10:
                return (R.drawable.normal_rain);

            case 11:
                return (R.drawable.rain_shower);

            case 12:
                return (R.drawable.rain);
            case 13:
                return (R.drawable.rain_snow);
            case 14:
                return (R.drawable.rain_snow);
            case 15:
                return (R.drawable.rain_snow);
            case 16:
                return (R.drawable.rain_snow);

            case 17:
                return (R.drawable.snow);
            case 18:
                return (R.drawable.snow);
            case 19:
                return (R.drawable.snow);
            case 20:
                return (R.drawable.mist);
            case 21:
                return (R.drawable.tornado);
            case 22:
                return (R.drawable.wind);
            case 23:
                return (R.drawable.wind);
            case 24:
                return (R.drawable.wind);
            case 25:
                return (R.drawable.wind);
            case 26:
                return (R.drawable.cloudy);
            case 27:
                return (R.drawable.night_cloud);
            case 28:
                return (R.drawable.cloud_day);

            case 29:
                return (R.drawable.night_cloud);
            case 30:
                return (R.drawable.cloud_day);
            case 31:
                return (R.drawable.night);
            case 32:
                return (R.drawable.sun_);
            case 33:
                return (R.drawable.night);
            case 34:
                return (R.drawable.sun_);
            case 35:
                return (R.drawable.rain_snow);
            case 36:
                return (R.drawable.sun_);
            case 37:
                return (R.drawable.thunderstorms);
            case 38:
                return (R.drawable.thunderstorms);
            case 39:
                return (R.drawable.day_rain);
            case 40:
                return (R.drawable.heavy_rain);
            case 41:
                return (R.drawable.snow);
            case 42:
                return (R.drawable.snow);
            case 43:
                return (R.drawable.snow);
            case 44:
                return (R.drawable.broken_clouds);
            case 45:
                return (R.drawable.rain_night);
            case 46:
                return (R.drawable.snow_night);
            case 47:
                return (R.drawable.thunderstorms);
            default:
                return 44;
        }
    }


    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setType("*.jpg");
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    private void init() {

         navigationView = (NavigationView) findViewById(R.id.navVIew_mainAcitivity);
        View headerView = navigationView.getHeaderView(0);
        ivNavigation = headerView.findViewById(R.id.iv_navCondtion);
        swCelciusFarenhit = headerView.findViewById(R.id.swCelciusFernhit_Navigation_mainActivity);
        tvCelcius =  headerView.findViewById(R.id.tvCelcius);
//        tvFernh
   tvFernhit = headerView.findViewById(R.id.tvFernhit);
                likeButton = findViewById(R.id.spark_button);
//        navUsername.setText("Your Text Here");
        drawerLayout = findViewById(R.id.drawerLayout_MainActivity);
        dbCity = new DBHelper_City(MainActivity.this);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        recyclerView = findViewById(R.id.rvDayForcasting_mainActivity);

        tvCurrentHigh = findViewById(R.id.tvHighTemp_mainActivity);
        tvCurrentLow = findViewById(R.id.tvLowTemp_mainActivity);
        tvConditon = findViewById(R.id.tvCondition_MainActivity);
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp_mainActivity);
        tvLoacation = findViewById(R.id.tvCurrentLocation_MainActivity);
        btnLikedCity = findViewById(R.id.btnInsertedItem_mainActivity);
        btnGps = findViewById(R.id.btnGpsLocation_MainActivity);
        ivImage = findViewById(R.id.ivCurrentWeather_MainActivity);
        tvChanceOfRain = findViewById(R.id.tvChancesOfRain_MainAcitvity);
        tvHumidity = findViewById(R.id.tvHumidity_MainAcitvity);
        tvSunrise = findViewById(R.id.tvSunrise_MainAcitvity);
        tvSunset = findViewById(R.id.tvSunset_MainAcitvity);
        tvWind = findViewById(R.id.tvWind_MainAcitvity);
        tvVisibility = findViewById(R.id.tvVisibility_MainAcitvity);
        tvPressure = findViewById(R.id.tvPressure_MainAcitvity);
        tvClouds = findViewById(R.id.tvClouds_MainAcitvity);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
    }


    @Override
    public void onLocationChanged(Location location) {
        latMain = location.getLatitude();
        langMain = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_FavoriteCity:
                MainActivity.this.startActivity(new Intent(MainActivity.this,LikedCityListActivity.class));
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final float COEFFICIENT = (float) 0.01;
        // Minimal x and y axis swipe distance.
        private int MIN_SWIPE_DISTANCE_X = 100;
        private int MIN_SWIPE_DISTANCE_Y = 100;

        // Maximal x and y axis swipe distance.
        private int MAX_SWIPE_DISTANCE_X = 1000;
        private int MAX_SWIPE_DISTANCE_Y = 1000;

        // Source activity that display message in text view.
        private MainActivity activity = null;

        public MainActivity getActivity() {
            return activity;
        }

        public void setActivity(MainActivity activity) {
            this.activity = activity;
        }

        /* This method is invoked when a swipe gesture happened. */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Get swipe delta value in x axis.
            float deltaX = e1.getX() - e2.getX();

            // Get swipe delta value in y axis.
            float deltaY = e1.getY() - e2.getY();

            // Get absolute value.
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);

            // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
//        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X))
//        {
//            if(deltaX > 0)
//            {
//                Toast.makeText(activity, "Swipe Left", Toast.LENGTH_SHORT).show();            }else
//            {
//                this.activity.displayMessage("Swipe to right");
//            }
//        }

//            if ((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y)) {
//                if (deltaY > 0) {
//                    decrementViewAlpha(drawerLayout,deltaY);
//                } else {
//                    incrementViewAlpha(drawerLayout,deltaY);
//                }
//            }


            return true;
        }

        private double calculateDeltaAlpha(float deltaX) {
            return deltaX * COEFFICIENT;
        }

        private void incrementViewAlpha(View view, float distanceX) {
//            float oldAlpha = drawerLayout.getAlpha();
//            if (oldAlpha > 0.29 && oldAlpha < 0.80) {
//                drawerLayout.setAlpha((float) (oldAlpha + calculateDeltaAlpha(distanceX)));
//            }
        }

        private void decrementViewAlpha(View view, float distanceX) {
//            float oldAlpha =drawerLayout.getAlpha();
//            if (oldAlpha > 0.29 && oldAlpha < 0.80) {
////                drawerLayout.setAlpha((float) (oldAlpha - calculateDeltaAlpha(distanceX)));
//            }
        }

        // Invoked when single tap screen.
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
//            Toast.makeText(activity, "Signal Occured", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Invoked when double tap screen.
        @Override
        public boolean onDoubleTap(MotionEvent e) {
//            Toast.makeText(activity, "Double tap occurred", Toast.LENGTH_SHORT).show();
//        this.activity.displayMessage(".");
            return true;
        }
    }
}

