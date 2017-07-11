package com.alia.weatherwidget.network;

import com.alia.weatherwidget.model.Wrapper;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alyona on 10.07.2017.
 */

public interface WeatherAPI {
     String BASE_URL = "http://api.openweathermap.org";

    @GET("/data/2.5/weather")
    Observable<Wrapper> getData(@Query("id") String cityId, @Query("units") String temperatureUnits, @Query("APPID") String authorizationKey);

}
