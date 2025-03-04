package com.aswdc_Dweather.POJOS.only_temp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseItem{

	@SerializedName("loc")
	private Loc loc;

	@SerializedName("profile")
	private Profile profile;

	@SerializedName("periods")
	private List<PeriodsItem> periods;

	@SerializedName("interval")
	private String interval;

	public void setLoc(Loc loc){
		this.loc = loc;
	}

	public Loc getLoc(){
		return loc;
	}

	public void setProfile(Profile profile){
		this.profile = profile;
	}

	public Profile getProfile(){
		return profile;
	}

	public void setPeriods(List<PeriodsItem> periods){
		this.periods = periods;
	}

	public List<PeriodsItem> getPeriods(){
		return periods;
	}

	public void setInterval(String interval){
		this.interval = interval;
	}

	public String getInterval(){
		return interval;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"loc = '" + loc + '\'' + 
			",profile = '" + profile + '\'' + 
			",periods = '" + periods + '\'' + 
			",interval = '" + interval + '\'' + 
			"}";
		}
}