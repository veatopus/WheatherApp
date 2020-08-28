package com.example.wheatherapp.ui.home_fragment;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wheatherapp.data.network.OpenWeatherService;
import com.example.wheatherapp.models.com.example.mab.ui.bottom_navigation.models.WeatherModel;
import com.example.wheatherapp.models.com.one_call.example.mab.ui.bottom_navigation.models.WeatherModelOneCall;

import retrofit2.Call;
import retrofit2.Response;

public class Presenter implements Contract.Presenter {
    private Contract.View mView;
    private Contract.Model mModel;

    public Presenter(Contract.View mView) {
        this.mView = mView;
        this.mModel = new Model();
    }

    @Override
    public void updateInformation() {
        mModel.uploadWeatherModel(new OpenWeatherService.OpenWeatherCallback() {
            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                mView.showWeatherModel(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void updateInformationOneCall(Double lat, Double lon) {
        mModel.uploadWeatherModelOneCall(lat, lon, new OpenWeatherService.OpenWeatherCallbackOneCall() {
            @Override
            public void onResponse(@NonNull Call<WeatherModelOneCall> call, @NonNull Response<WeatherModelOneCall> response) {
                mView.showWeatherModelOneCall(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModelOneCall> call, @NonNull Throwable t) {
                Log.e("ololo", "onFailure (one call): ", t);
            }
        });
    }

}