package com.alia.weatherwidget;

import android.app.Application;

import com.alia.weatherwidget.network.RetrofitService;

/**
 * Created by Alyona on 10.07.2017.
 */

public class App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        RetrofitService.initInstance();
    }
}
