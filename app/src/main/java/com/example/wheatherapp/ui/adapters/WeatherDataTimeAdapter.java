package com.example.wheatherapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wheatherapp.R;
import com.example.wheatherapp.models.data.TimeModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataTimeAdapter extends RecyclerView.Adapter<WeatherDataTimeAdapter.TimeViewHolder> {
    Context context;
    List<TimeModel> data = new ArrayList<>();

    public WeatherDataTimeAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<TimeModel> list){
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll(){
        data.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class TimeViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view_weather_icon;
        TextView text_view_degree_celsius;
        TextView text_view_time;
        TextView text_view_description;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view_weather_icon = itemView.findViewById(R.id.image_view_weather_icon);
            text_view_degree_celsius = itemView.findViewById(R.id.text_view_degree_celsius);
            text_view_time = itemView.findViewById(R.id.text_view_time);
            text_view_description = itemView.findViewById(R.id.text_view_description);
        }

        public void onBind(TimeModel model){
            text_view_degree_celsius.setText(model.getTemperature());
            text_view_time.setText(model.getTime());
            text_view_description.setText(model.getDescription());
            Glide
                    .with(context)
                    .load(model.getIconUrl())
                    .into(image_view_weather_icon);
        }
    }
}