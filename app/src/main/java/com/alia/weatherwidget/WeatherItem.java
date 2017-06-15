package com.alia.weatherwidget;


public class WeatherItem {
    private String mTempMin;
    private String mTempMax;
    private String mDescription;


    public String getTempMin() {
        return mTempMin;
    }

    public void setTempMin(String tempMin) {
        mTempMin = tempMin;
    }

    public String getTempMax() {
        return mTempMax;
    }

    public void setTempMax(String tempMax) {
        mTempMax = tempMax;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
