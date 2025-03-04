package com.aswdc_Dweather.Beans;

public class SuggestionsItem {
    String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public SuggestionsItem(String cityName) {

        this.cityName = cityName;
    }
}
