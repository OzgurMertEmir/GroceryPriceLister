package com.example.grocerypricelister;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class Market {

    private String name;
    private String adress;
    private double latitude;
    private double longtitude;
    private LatLng location;
    private boolean isOpen;

    public Market(HashMap<String, String > properties)
    {
        name = properties.get("name");
        latitude = Double.valueOf(properties.get("latitude"));
        longtitude = Double.valueOf(properties.get("longitude"));
        location = new LatLng(latitude, longtitude );
        adress = properties.get("adress");
    }

    public String getName(){ return name; }

    public String getAdress(){ return adress; }

    public double getLatitude() { return latitude; }

    public double getLongtitude() { return longtitude; }

    public LatLng getLocation() { return location; }

    public boolean isOpen(){ return isOpen; }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpen(boolean a) {
        this.isOpen = a;
    }
}
