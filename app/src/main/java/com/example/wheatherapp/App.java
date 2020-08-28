package com.example.wheatherapp;

import android.app.Application;

import com.example.wheatherapp.data.network.OpenWeatherService;

public class App extends Application {
    OpenWeatherService weatherService;
    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        weatherService = new OpenWeatherService();
    }

    public static App getInstance() {
        return instance;
    }

    public OpenWeatherService getWeatherService() {
        return weatherService;
    }
}
