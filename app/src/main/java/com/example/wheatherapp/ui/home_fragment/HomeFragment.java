package com.example.wheatherapp.ui.home_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wheatherapp.Const;
import com.example.wheatherapp.R;
import com.example.wheatherapp.models.com.example.mab.ui.bottom_navigation.models.WeatherModel;
import com.example.wheatherapp.models.com.one_call.example.mab.ui.bottom_navigation.models.Daily;
import com.example.wheatherapp.models.com.one_call.example.mab.ui.bottom_navigation.models.Hourly;
import com.example.wheatherapp.models.com.one_call.example.mab.ui.bottom_navigation.models.WeatherModelOneCall;
import com.example.wheatherapp.models.data.TimeModel;
import com.example.wheatherapp.ui.adapters.WeatherDataTimeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements Contract.View {
    @BindView(R.id.degree_celsius)
    TextView textViewDegreeCelsius;
    @BindView(R.id.text_view_weather_description)
    TextView textViewWeatherDescription;
    @BindView(R.id.image_view_weather_icon)
    ImageView imageViewWeatherIcon;
    @BindView(R.id.btn_city_name)
    Button buttonCityName;
    @BindView(R.id.text_view_humidity)
    TextView textViewHumidity;
    @BindView(R.id.text_view_uv_index)
    TextView textViewUvIndex;
    @BindView(R.id.text_view_wind_speed)
    TextView textViewWindSpeed;
    @BindView(R.id.text_view_today)
    TextView textViewToday;
    @BindView(R.id.text_view_tomorrow)
    TextView textViewTomorrow;
    @BindView(R.id.text_view_see_all_day)
    TextView textViewSeeAllDay;
    @BindView(R.id.text_view_see_all_week)
    TextView textViewSeeAllWeek;
    @BindView(R.id.image_view_6am)
    ImageView imageView6am;
    @BindView(R.id.image_view_12am)
    ImageView imageView12am;
    @BindView(R.id.image_view_18am)
    ImageView imageView18am;
    @BindView(R.id.image_view_24am)
    ImageView imageView24am;
    @BindView(R.id.recyclerview_hourly)
    RecyclerView recyclerViewHourly;
    @BindView(R.id.recyclerview_daily)
    RecyclerView recyclerViewDaily;
    WeatherDataTimeAdapter timeAdapterHourly;
    WeatherDataTimeAdapter timeAdapterDaily;
    Presenter presenter = new Presenter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.updateInformation();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        timeAdapterHourly = new WeatherDataTimeAdapter(requireContext());
        timeAdapterDaily = new WeatherDataTimeAdapter(requireContext());

        recyclerViewHourly.setAdapter(timeAdapterHourly);
        recyclerViewDaily.setAdapter(timeAdapterDaily);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showWeatherModel(WeatherModel weatherModel) {
        textViewDegreeCelsius.setText(weatherModel.getMain().getTemp().toString() + Const.CELSIUS);
        textViewWeatherDescription.setText(weatherModel.getWeather().get(0).getDescription());
        String urlWeatherIcon = "http://openweathermap.org/img/wn/" + weatherModel.getWeather().get(0).getIcon() + "@2x.png";
        Glide
                .with(this)
                .load(urlWeatherIcon)
                .into(imageViewWeatherIcon);
        textViewHumidity.setText(weatherModel.getMain().getHumidity().toString());
        textViewWindSpeed.setText(weatherModel.getWind().getSpeed().toString());
        presenter.updateInformationOneCall(weatherModel.getCoord().getLat(), weatherModel.getCoord().getLon());
    }

    @Override
    public void showWeatherModelOneCall(WeatherModelOneCall weatherModel) {
        List<TimeModel> dataDaily = new ArrayList<>();
        for (Daily daily : weatherModel.getDaily()) {
            long dv = Long.valueOf(daily.getDt()) * 1000;// its need to be in milisecond
            Date df = new java.util.Date(dv);
            @SuppressLint("SimpleDateFormat")
            String vv = new SimpleDateFormat("MMM dd").format(df);
            String urlWeatherIcon = "http://openweathermap.org/img/wn/" + daily.getWeather().get(0).getIcon() + "@2x.png";
            dataDaily.add(new TimeModel(daily.getTemp().getDay().toString() + Const.CELSIUS, urlWeatherIcon, vv, daily.getWeather().get(0).getDescription()));
        }

        List<TimeModel> dataHourly = new ArrayList<>();
        for (Hourly hourly : weatherModel.getHourly()) {
            long dv = Long.valueOf(hourly.getDt()) * 1000;
            Date df = new java.util.Date(dv);
            @SuppressLint("SimpleDateFormat")
            String vv = new SimpleDateFormat("dd, hh:mm").format(df);
            String urlWeatherIcon = "http://openweathermap.org/img/wn/" + hourly.getWeather().get(0).getIcon() + "@2x.png";
            dataHourly.add(new TimeModel(hourly.getTemp().toString() + Const.CELSIUS, urlWeatherIcon, vv, hourly.getWeather().get(0).getDescription()));
        }

        Glide
                .with(this)
                .load(dataHourly.get(12).getIconUrl())
                .into(imageView12am);
        Glide
                .with(this)
                .load(dataHourly.get(6).getIconUrl())
                .into(imageView6am);
        Glide
                .with(this)
                .load(dataHourly.get(18).getIconUrl())
                .into(imageView18am);

        Glide
                .with(this)
                .load(dataHourly.get(24).getIconUrl())
                .into(imageView24am);


        timeAdapterHourly.addList(dataHourly);
        timeAdapterDaily.addList(dataDaily);
    }
}