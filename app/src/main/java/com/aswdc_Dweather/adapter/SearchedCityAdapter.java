package com.aswdc_Dweather.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aswdc_Dweather.Activity.LikedCityListActivity;
import com.aswdc_Dweather.Activity.MainActivity;
import com.aswdc_Dweather.POJOS.Forcast.Main;
import com.aswdc_Dweather.POJOS.yahho_weather_reponse.ResponseOfYahhoWeatherApi;
import com.aswdc_Dweather.R;
import com.aswdc_Dweather.dbHelper.DBHelper_City;
import com.aswdc_Dweather.utility.Constants;

import java.util.List;

import static com.aswdc_Dweather.Activity.MainActivity.searchItemClick;

public class SearchedCityAdapter extends RecyclerView.Adapter<SearchedCityAdapter.ViewHolder> {

    List<ResponseOfYahhoWeatherApi> listItems;
    Context context;
    DBHelper_City dbb;
    ResponseOfYahhoWeatherApi item;
    double lat;
    double lon;

    public SearchedCityAdapter(List<ResponseOfYahhoWeatherApi> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        dbb = new DBHelper_City(context);
    }

    @NonNull
    @Override
    public SearchedCityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.searched_city_item, viewGroup, false);
        SearchedCityAdapter.ViewHolder vh = new SearchedCityAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchedCityAdapter.ViewHolder viewHolder, final int i) {
        item = listItems.get(i);
        final   ResponseOfYahhoWeatherApi indiVidual=item;



//        setOpenWeatherConditonImage();

        switch (item.getForecasts().get(0).getCode() ) {
            case 0:
                viewHolder.ivImage.setImageResource(R.drawable.tornado);
                break;
            case 1:
                viewHolder.ivImage.setImageResource(R.drawable.tornado);
                break;
            case 2:
                viewHolder.ivImage.setImageResource(R.drawable.tornado);
                break;
            case 3:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_thundered);
                break;
            case 4:
                viewHolder.ivImage.setImageResource(R.drawable.thundered);
                break;
            case 5:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 6:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 7:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 8:
                viewHolder.ivImage.setImageResource(R.drawable.mist);
                break;
            case 9:
                viewHolder.ivImage.setImageResource(R.drawable.mist);
                break;
            case 10:
                viewHolder.ivImage.setImageResource(R.drawable.light_rain);
                break;
            case 11:
                viewHolder.ivImage.setImageResource(R.drawable.shower_rain);
                break;
            case 12:
                viewHolder.ivImage.setImageResource(R.drawable.light_rain);
                break;
            case 13:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 14:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 15:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 16:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 17:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 18:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 19:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 20:
                viewHolder.ivImage.setImageResource(R.drawable.mist);
                break;
            case 21:
                viewHolder.ivImage.setImageResource(R.drawable.tornado);
                break;
            case 22:
                viewHolder.ivImage.setImageResource(R.drawable.wind);
                break;
            case 23:
                viewHolder.ivImage.setImageResource(R.drawable.wind);
                break;
            case 24:
                viewHolder.ivImage.setImageResource(R.drawable.wind);
                break;
            case 25:
                viewHolder.ivImage.setImageResource(R.drawable.wind);
                break;
            case 26:
                viewHolder.ivImage.setImageResource(R.drawable.clouds);
                break;
            case 27:
                viewHolder.ivImage.setImageResource(R.drawable.night_with_cloud);
                break;
            case 28:
                viewHolder.ivImage.setImageResource(R.drawable.clouds);
                break;
            case 29:
                viewHolder.ivImage.setImageResource(R.drawable.night_with_cloud);
                break;
            case 30:
                viewHolder.ivImage.setImageResource(R.drawable.sun_few_clouds);
                break;
            case 31:
                viewHolder.ivImage.setImageResource(R.drawable.night);
                break;
            case 32:
                viewHolder.ivImage.setImageResource(R.drawable.clear_sky);
                break;
            case 33:
                viewHolder.ivImage.setImageResource(R.drawable.night);
                break;
            case 34:
                viewHolder.ivImage.setImageResource(R.drawable.clear_sky);
                break;
            case 35:
                viewHolder.ivImage.setImageResource(R.drawable.rain_with_snow);
                break;
            case 36:
                viewHolder.ivImage.setImageResource(R.drawable.clear_sky);
                break;
            case 37:
                viewHolder.ivImage.setImageResource(R.drawable.thundered);
                break;
            case 38:
                viewHolder.ivImage.setImageResource(R.drawable.thundered);
                break;
            case 39:
                viewHolder.ivImage.setImageResource(R.drawable.day_rain);
                break;
            case 40:
                viewHolder.ivImage.setImageResource(R.drawable.heavy_rain);
                break;
            case 41:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 42:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 43:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 44:
                viewHolder.ivImage.setImageResource(R.drawable.broken_clouds);
                break;
            case 45:
                viewHolder.ivImage.setImageResource(R.drawable.night_with_rain);
                break;
            case 46:
                viewHolder.ivImage.setImageResource(R.drawable.snow);
                break;
            case 47:
                viewHolder.ivImage.setImageResource(R.drawable.thundered);
                break;

        }

        viewHolder.ivImage.setImageResource(MainActivity.getImageId(item.getCurrentObservation().getCondition().getCode()));
        viewHolder.tvCurrentTemp.setText(item.getCurrentObservation().getCondition().getTemperature() + Constants.CELSIUS);
        viewHolder.tvMax.setText(String.valueOf(item.getForecasts().get(0).getHigh()));
        viewHolder.tvMin.setText(String.valueOf(item.getForecasts().get(0).getLow()));
        viewHolder.tvCity.setText(String.valueOf(item.getLocation().getCity()));
        viewHolder.likeButton.setChecked(true);
        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItems.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, listItems.size());
                dbb.deleteLocation(indiVidual.getLocation().getCity());
                if (MainActivity.tvLoacation.getText().toString().equals(indiVidual.getLocation().getCity())){
                    MainActivity.likeButton.setChecked(false);
                }

            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItemClick = true;
                MainActivity.lat = indiVidual.getLocation().getLat();
                MainActivity.lan = indiVidual.getLocation().getJsonMemberLong();
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public  void setOpenWeatherConditonImage(String icon, ImageView imageView) {

        if (icon.equals("01d")) {
            imageView.setImageResource(R.drawable.clear_sky);
        }
        if (icon.equals("01n")) {
            imageView.setImageResource(R.drawable.night);
        }
        if (icon.equals("02d")) {
            imageView.setImageResource(R.drawable.sun_few_clouds);

        }
        if (icon.equals("02n")) {
            imageView.setImageResource(R.drawable.night_with_cloud);

        }
        if (icon.equals("03d") || icon.equals("03n")) {
            imageView.setImageResource(R.drawable.clouds);

        }
        if (icon.equals("04d") || icon.equals("04n")) {
            imageView.setImageResource(R.drawable.broken_clouds);

        }
        if (icon.equals("09n") || icon.equals("09d")) {
            imageView.setImageResource(R.drawable.light_rain);

        }
        if (icon.equals("10d")) {
            imageView.setImageResource(R.drawable.sun_with_rain);

        }
        if (icon.equals("10n")) {
            imageView.setImageResource(R.drawable.night_with_rain);

        }
        if (icon.equals("11d")||icon.equals("11n")) {
            imageView.setImageResource(R.drawable.rain_with_thundered);

        }
        if (icon.equals("13d")||icon.equals("13n")) {
            imageView.setImageResource(R.drawable.snow);

        }
        if (icon.equals("50d")||icon.equals("50n")) {
            imageView.setImageResource(R.drawable.mist);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        com.varunest.sparkbutton.SparkButton likeButton;
        TextView tvMax, tvMin, tvCurrent, tvCurrentTemp, tvCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_weatherInfo_SearchedCityItem);
            likeButton = itemView.findViewById(R.id.btnLike_SearchedItem);
            tvMax = itemView.findViewById(R.id.tv_max_searchedCityItem);
            tvCurrent = itemView.findViewById(R.id.tv_CurrentTemp_searchedCityItem);
            tvMin = itemView.findViewById(R.id.tv_min_searchedCityItem);
            tvCity = itemView.findViewById(R.id.tv_City_searchedCityItem);
            tvCurrentTemp = itemView.findViewById(R.id.tv_weatherDatails_searchedCityItem);
        }
    }
}
