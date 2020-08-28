package com.example.wheatherapp.models.data;

public class TimeModel {
    private String temperature;
    private String iconUrl;
    private String time;
    private String description;

    public TimeModel(String temperature, String iconUrl, String time, String description) {
        this.temperature = temperature;
        this.iconUrl = iconUrl;
        this.time = time;
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
