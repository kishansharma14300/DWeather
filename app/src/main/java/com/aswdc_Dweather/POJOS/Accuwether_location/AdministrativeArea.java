package com.aswdc_Dweather.POJOS.Accuwether_location;

import com.google.gson.annotations.SerializedName;

public class AdministrativeArea{

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("ID")
	private String iD;

	public void setLocalizedName(String localizedName){
		this.localizedName = localizedName;
	}

	public String getLocalizedName(){
		return localizedName;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	@Override
 	public String toString(){
		return 
			"AdministrativeArea{" + 
			"localizedName = '" + localizedName + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}