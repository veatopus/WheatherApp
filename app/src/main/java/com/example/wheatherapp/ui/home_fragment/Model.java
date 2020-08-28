package com.example.wheatherapp.ui.home_fragment;

import com.example.wheatherapp.App;
import com.example.wheatherapp.Const;
import com.example.wheatherapp.data.network.OpenWeatherService;

public class Model implements Contract.Model{

    @Override
    public void uploadWeatherModel(OpenWeatherService.OpenWeatherCallback listener) {
        App.getInstance().getWeatherService().searchCity("bishkek", Const.API_KEY, "ru", "metric", listener);
    }

    @Override
    public void uploadWeatherModelOneCall(Double lat, Double lon, OpenWeatherService.OpenWeatherCallbackOneCall listener) {
        App.getInstance().getWeatherService().oneCall(lat, lon, listener);
    }

}