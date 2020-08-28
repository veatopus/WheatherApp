package com.example.wheatherapp.data.network;

import androidx.annotation.NonNull;

import com.example.wheatherapp.Const;
import com.example.wheatherapp.models.com.example.mab.ui.bottom_navigation.models.WeatherModel;
import com.example.wheatherapp.models.com.one_call.example.mab.ui.bottom_navigation.models.WeatherModelOneCall;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class OpenWeatherService {

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES).build();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http:/api.openweathermap.org/")
            .client(client)
            .build();

    private OpenWeatherApi service = retrofit.create(OpenWeatherApi.class);

    public void searchCity(String q, String appid, String lang, String units, final OpenWeatherCallback callback) {
        service.searchCity(q, appid, lang, units)
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                        callback.onResponse(call, response);

                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                        callback.onFailure(call, t);
                    }
                });
    }

    public void oneCall(Double lat, Double lon, OpenWeatherCallbackOneCall callbackOneCall) {
        service.oneCall(lat, lon, Const.API_KEY, "ru", "metric")
                .enqueue(new Callback<WeatherModelOneCall>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherModelOneCall> call, @NonNull Response<WeatherModelOneCall> response) {
                        callbackOneCall.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherModelOneCall> call, @NonNull Throwable t) {
                        callbackOneCall.onFailure(call, t);
                    }
                });
    }

    public interface OpenWeatherApi {
        //api.openweathermap.org/data/2.5/weather?q=Bishkek&appid=4bbf5a1ed98cd9f688ebb3651474cdd2&lang=ru&units=metric

        @GET("data/2.5/weather")
        Call<WeatherModel> searchCity(
                @Query("q") String q,
                @Query("appid") String appid,
                @Query("lang") String lang,
                @Query("units") String units
        );

        //http://openweathermap.org/img/wn/10d@2x.png

        /*@GET("img/wn/{id}@2x.png")
        Call<String>*/

        //https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}
        //&appid=4bbf5a1ed98cd9f688ebb3651474cdd2&lang=ru&units=metric
        @GET("data/2.5/onecall")
        Call<WeatherModelOneCall> oneCall(
                @Query("lat") Double lat,
                @Query("lon") Double lon,
                @Query("appid") String appid,
                @Query("lang") String lang,
                @Query("units") String units
        );
    }

    public interface OpenWeatherCallback {
        void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response);

        void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t);
    }

    public interface OpenWeatherCallbackOneCall {
        void onResponse(@NonNull Call<WeatherModelOneCall> call, @NonNull Response<WeatherModelOneCall> response);

        void onFailure(@NonNull Call<WeatherModelOneCall> call, @NonNull Throwable t);
    }
}