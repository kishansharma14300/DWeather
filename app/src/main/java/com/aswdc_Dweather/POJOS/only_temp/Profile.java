package com.aswdc_Dweather.POJOS.only_temp;

import com.google.gson.annotations.SerializedName;

public class Profile{

	@SerializedName("tz")
	private String tz;

	public void setTz(String tz){
		this.tz = tz;
	}

	public String getTz(){
		return tz;
	}

	@Override
 	public String toString(){
		return 
			"Profile{" + 
			"tz = '" + tz + '\'' + 
			"}";
		}
}