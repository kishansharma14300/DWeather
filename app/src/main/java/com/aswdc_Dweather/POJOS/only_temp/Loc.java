package com.aswdc_Dweather.POJOS.only_temp;

import com.google.gson.annotations.SerializedName;

public class Loc{

	@SerializedName("long")
	private double jsonMemberLong;

	@SerializedName("latItemClick")
	private double lat;

	public void setJsonMemberLong(double jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public double getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Loc{" + 
			"long = '" + jsonMemberLong + '\'' + 
			",latItemClick = '" + lat + '\'' +
			"}";
		}
}