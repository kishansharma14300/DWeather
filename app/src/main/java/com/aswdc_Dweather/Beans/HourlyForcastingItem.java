package com.aswdc_Dweather.Beans;

public class HourlyForcastingItem {
    String icon;
    String maxTemp;
    String minTemp;
    String time;

    public HourlyForcastingItem(String icon, String maxTemp, String minTemp, String time) {
        this.icon = icon;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
