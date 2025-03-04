package com.aswdc_Dweather.Beans.currentData;

import com.google.gson.annotations.SerializedName;

public class Coord{

	@SerializedName("lonItemClick")
	private double lon;

	@SerializedName("latItemClick")
	private double lat;

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
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
			"Coord{" + 
			"lonItemClick = '" + lon + '\'' +
			",latItemClick = '" + lat + '\'' +
			"}";
		}
}