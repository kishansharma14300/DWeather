package com.aswdc_Dweather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ForecastsItem;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ResponseOfYahhoWeatherApi;
import com.aswdc_Dweather.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Callback;

public class FourDayForcastingAdapter extends RecyclerView.Adapter<FourDayForcastingAdapter.ViewHolder> {

    List<ForecastsItem> forecastsList;
    Context context;
    ForecastsItem item;

    public FourDayForcastingAdapter(List<ForecastsItem> forecastsList, Context context) {

        this.forecastsList = forecastsList;
        this.context = context;
    }

    @NonNull
    @Override
    public FourDayForcastingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.fourday_forcast_item, viewGroup, false);
        return new FourDayForcastingAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FourDayForcastingAdapter.ViewHolder viewHolder, int i) {
        item = forecastsList.get(i);
        viewHolder.tvMax.setText(String.valueOf(item.getHigh()));
        viewHolder.tvMin.setText(String.valueOf(item.getLow()));

//        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//        cal.setTimeInMillis(item.getDate() * 1000);
//        android.text.format.DateFormat df = new android.text.format.DateFormat();
//        String date = df.format("hh:mm a", cal).toString();
//
//        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
//        String day = outFormat.format(date);
        viewHolder.tvDay.setText(item.getDay());
        int code = item.getCode();
//0 	tornado
//1 	tropical storm
//2 	hurricane
//3 	severe thundered
//4 	thundered
//5 	mixed rain and snow
//6 	mixed rain and sleet
//7 	mixed snow and sleet
//8 	freezing drizzle
//9 	drizzle
//10 	freezing rain
//11 	showers
//12 	rain
//13 	snow flurries
//14 	light snow showers
//15 	blowing snow
//16 	snow
//17 	hail
//18 	sleet
//19 	dust
//20 	foggy
//21 	haze
//22 	smoky
//23 	blustery
//24 	windy
//25 	cold
//26 	cloudy
//27 	mostly cloudy (night)
//28 	mostly cloudy (day)
//29 	partly cloudy (night)
//30 	partly cloudy (day)
//31 	clear (night)
//32 	sunny
//33 	fair (night)
//34 	fair (day)
//35 	mixed rain and hail
//36 	hot
//37 	isolated thundered
//38 	scattered thundered
//39 	scattered showers (day)
//40 	heavy rain
//41 	scattered snow showers (day)
//42 	heavy snow
//43 	blizzard
//44 	not available
//45 	scattered showers (night)
//46 	scattered snow showers (night)
//47 	scattered thundershowers
       switch (code) {
            case 0:
                viewHolder.ivWeather.setImageResource(R.drawable.tornado);
                break;
            case 1:
                viewHolder.ivWeather.setImageResource(R.drawable.tornado);
                break;
            case 2:
                viewHolder.ivWeather.setImageResource(R.drawable.tornado);
                break;
            case 3:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_thundered);
                break;
            case 4:
                viewHolder.ivWeather.setImageResource(R.drawable.thundered);
                break;
            case 5:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 6:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 7:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 8:
                viewHolder.ivWeather.setImageResource(R.drawable.mist);
                break;
            case 9:
                viewHolder.ivWeather.setImageResource(R.drawable.mist);
                break;
            case 10:
                viewHolder.ivWeather.setImageResource(R.drawable.light_rain);
                break;
            case 11:
                viewHolder.ivWeather.setImageResource(R.drawable.shower_rain);
                break;
            case 12:
                viewHolder.ivWeather.setImageResource(R.drawable.light_rain);
                break;
            case 13:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 14:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 15:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 16:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 17:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 18:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 19:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 20:
                viewHolder.ivWeather.setImageResource(R.drawable.mist);
                break;
            case 21:
                viewHolder.ivWeather.setImageResource(R.drawable.tornado);
                break;
            case 22:
                viewHolder.ivWeather.setImageResource(R.drawable.wind);
                break;
            case 23:
                viewHolder.ivWeather.setImageResource(R.drawable.wind);
                break;
            case 24:
                viewHolder.ivWeather.setImageResource(R.drawable.wind);
                break;
            case 25:
                viewHolder.ivWeather.setImageResource(R.drawable.wind);
                break;
            case 26:
                viewHolder.ivWeather.setImageResource(R.drawable.clouds);
                break;
            case 27:
                viewHolder.ivWeather.setImageResource(R.drawable.night_with_cloud);
                break;
            case 28:
                viewHolder.ivWeather.setImageResource(R.drawable.clouds);
                break;
            case 29:
                viewHolder.ivWeather.setImageResource(R.drawable.night_with_cloud);
                break;
            case 30:
                viewHolder.ivWeather.setImageResource(R.drawable.sun_few_clouds);
                break;
            case 31:
                viewHolder.ivWeather.setImageResource(R.drawable.night);
                break;
            case 32:
                viewHolder.ivWeather.setImageResource(R.drawable.clear_sky);
                break;
            case 33:
                viewHolder.ivWeather.setImageResource(R.drawable.night);
                break;
            case 34:
                viewHolder.ivWeather.setImageResource(R.drawable.clear_sky);
                break;
            case 35:
                viewHolder.ivWeather.setImageResource(R.drawable.rain_with_snow);
                break;
            case 36:
                viewHolder.ivWeather.setImageResource(R.drawable.clear_sky);
                break;
            case 37:
                viewHolder.ivWeather.setImageResource(R.drawable.thundered);
                break;
            case 38:
                viewHolder.ivWeather.setImageResource(R.drawable.thundered);
                break;
            case 39:
                viewHolder.ivWeather.setImageResource(R.drawable.day_rain);
                break;
            case 40:
                viewHolder.ivWeather.setImageResource(R.drawable.heavy_rain);
                break;
            case 41:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 42:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 43:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 44:
                viewHolder.ivWeather.setImageResource(R.drawable.broken_clouds);
                break;
            case 45:
                viewHolder.ivWeather.setImageResource(R.drawable.night_with_rain);
                break;
            case 46:
                viewHolder.ivWeather.setImageResource(R.drawable.snow);
                break;
            case 47:
                viewHolder.ivWeather.setImageResource(R.drawable.thundered);
                break;

        }

    }

    private String getDay(String day) {
        if (day.equals("Sun"))day="Sunday";
        if (day.equals("Mon"))day="Monday";
        if (day.equals("Tue"))day="Tuesday";
        if (day.equals("Wed"))day="Wednesday";
        if (day.equals("Thu"))day="Thursday";
        if (day.equals("Fri"))day="Friday";
        if (day.equals("Sat"))day="Saturday";
        return  day;
    }

    @Override
    public int getItemCount() {
        return forecastsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvMax, tvMin;
        ImageView ivWeather;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_dayName_FourDayForcastItem);
            tvMax = itemView.findViewById(R.id.tv_high_ForcastingItem);
            tvMin = itemView.findViewById(R.id.tv_low_ForcastingItem);
            ivWeather = itemView.findViewById(R.id.iv_weatherDetailIcon_FourDayForcastItem);
        }
    }
}
