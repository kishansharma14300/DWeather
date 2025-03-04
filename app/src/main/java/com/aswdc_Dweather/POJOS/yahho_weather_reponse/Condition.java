package com.aswdc_Dweather.POJOS.yahho_weather_reponse;

import com.google.gson.annotations.SerializedName;

public class Condition{

	@SerializedName("code")
	private int code;

	@SerializedName("temperature")
	private int temperature;

	@SerializedName("text")
	private String text;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setTemperature(int temperature){
		this.temperature = temperature;
	}

	public int getTemperature(){
		return temperature;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"Condition{" + 
			"code = '" + code + '\'' + 
			",temperature = '" + temperature + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}