package com.example.wheatherapp.ui.home_fragment;

import com.example.wheatherapp.data.network.OpenWeatherService;
import com.example.wheatherapp.models.com.example.mab.ui.bottom_navigation.models.WeatherModel;
import com.example.wheatherapp.models.com.one_call.example.mab.ui.bottom_navigation.models.WeatherModelOneCall;

public interface Contract {

    interface View{
        void showWeatherModel(WeatherModel weatherModel);
        void showWeatherModelOneCall(WeatherModelOneCall weatherModel);
    }

    interface Presenter{
        void updateInformation();
        void updateInformationOneCall(Double lat, Double lon);
    }

    interface Model{
        void uploadWeatherModel(OpenWeatherService.OpenWeatherCallback listener);
        void uploadWeatherModelOneCall(Double lat, Double lon, OpenWeatherService.OpenWeatherCallbackOneCall listener);
    }
}