package com.example.myapplication.Interface;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class DirectionsResponse {
    private List<LatLng> routePoints;
    private String distance;
    private String duration;
    // Các trường khác tùy ý

    public List<LatLng> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<LatLng> routePoints) {
        this.routePoints = routePoints;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Các phương thức getter và setter cho các trường khác
}