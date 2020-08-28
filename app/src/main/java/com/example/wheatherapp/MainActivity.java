package com.example.wheatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wheatherapp.data.network.OpenWeatherService;
import com.example.wheatherapp.models.com.example.mab.ui.bottom_navigation.models.WeatherModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OpenWeatherService.OpenWeatherCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenWeatherService service = new OpenWeatherService();
        service.searchCity("bishkek", Const.API_KEY, "ru", "metric", this);
    }

    @Override
    public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
        assert response.body() != null;
        Log.e("ololo", "onResponse: " + response.body().getWeather().get(0).getDescription());
    }

    @Override
    public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
        Log.e("ololo", "onFailure: ", t);
    }

}