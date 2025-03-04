package com.aswdc_Dweather.POJOS.yahho_weather_reponse;

import com.google.gson.annotations.SerializedName;

public class Wind{

	@SerializedName("chill")
	private int chill;

	@SerializedName("speed")
	private double speed;

	@SerializedName("direction")
	private int direction;

	public void setChill(int chill){
		this.chill = chill;
	}

	public int getChill(){
		return chill;
	}

	public void setSpeed(double speed){
		this.speed = speed;
	}

	public double getSpeed(){
		return speed;
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	public int getDirection(){
		return direction;
	}

	@Override
 	public String toString(){
		return 
			"Wind{" + 
			"chill = '" + chill + '\'' + 
			",speed = '" + speed + '\'' + 
			",direction = '" + direction + '\'' + 
			"}";
		}
}