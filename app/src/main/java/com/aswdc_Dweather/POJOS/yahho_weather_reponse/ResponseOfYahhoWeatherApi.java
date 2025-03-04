package com.aswdc_Dweather.POJOS.yahho_weather_reponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseOfYahhoWeatherApi{

	@SerializedName("location")
	private Location location;

	@SerializedName("current_observation")
	private CurrentObservation currentObservation;

	@SerializedName("forecasts")
	private List<ForecastsItem> forecasts;

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setCurrentObservation(CurrentObservation currentObservation){
		this.currentObservation = currentObservation;
	}

	public CurrentObservation getCurrentObservation(){
		return currentObservation;
	}

	public void setForecasts(List<ForecastsItem> forecasts){
		this.forecasts = forecasts;
	}

	public List<ForecastsItem> getForecasts(){
		return forecasts;
	}

	@Override
 	public String toString(){
		return 
			"ResponseOfYahhoWeatherApi{" + 
			"location = '" + location + '\'' + 
			",current_observation = '" + currentObservation + '\'' + 
			",forecasts = '" + forecasts + '\'' + 
			"}";
		}
}