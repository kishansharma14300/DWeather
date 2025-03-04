package com.aswdc_Dweather.POJOS.Accuwether_location;

import com.google.gson.annotations.SerializedName;

public class ResponseOfAutoCompleteTextViewFromAccuWeather{

	@SerializedName("AdministrativeArea")
	private AdministrativeArea administrativeArea;

	@SerializedName("Type")
	private String type;

	@SerializedName("Version")
	private int version;

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("Country")
	private Country country;

	@SerializedName("Rank")
	private int rank;

	@SerializedName("Key")
	private String key;

	public void setAdministrativeArea(AdministrativeArea administrativeArea){
		this.administrativeArea = administrativeArea;
	}

	public AdministrativeArea getAdministrativeArea(){
		return administrativeArea;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setVersion(int version){
		this.version = version;
	}

	public int getVersion(){
		return version;
	}

	public void setLocalizedName(String localizedName){
		this.localizedName = localizedName;
	}

	public String getLocalizedName(){
		return localizedName;
	}

	public void setCountry(Country country){
		this.country = country;
	}

	public Country getCountry(){
		return country;
	}

	public void setRank(int rank){
		this.rank = rank;
	}

	public int getRank(){
		return rank;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	@Override
 	public String toString(){
		return 
			"ResponseOfAutoCompleteTextViewFromAccuWeather{" + 
			"administrativeArea = '" + administrativeArea + '\'' + 
			",type = '" + type + '\'' + 
			",version = '" + version + '\'' + 
			",localizedName = '" + localizedName + '\'' + 
			",country = '" + country + '\'' + 
			",rank = '" + rank + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}
}