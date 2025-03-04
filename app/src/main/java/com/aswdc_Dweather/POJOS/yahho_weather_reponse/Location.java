package com.aswdc_Dweather.POJOS.yahho_weather_reponse;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("country")
	private String country;

	@SerializedName("city")
	private String city;

	@SerializedName("woeid")
	private int woeid;

	@SerializedName("timezone_id")
	private String timezoneId;

	@SerializedName("region")
	private String region;

	@SerializedName("lat")
	private double lat;

	@SerializedName("long")
	private double jsonMemberLong;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setWoeid(int woeid){
		this.woeid = woeid;
	}

	public int getWoeid(){
		return woeid;
	}

	public void setTimezoneId(String timezoneId){
		this.timezoneId = timezoneId;
	}

	public String getTimezoneId(){
		return timezoneId;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	public void setJsonMemberLong(double jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public double getJsonMemberLong(){
		return jsonMemberLong;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"country = '" + country + '\'' + 
			",city = '" + city + '\'' + 
			",woeid = '" + woeid + '\'' + 
			",timezone_id = '" + timezoneId + '\'' + 
			",region = '" + region + '\'' + 
			",latItemClick = '" + lat + '\'' +
			",long = '" + jsonMemberLong + '\'' + 
			"}";
		}
}