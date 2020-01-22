package com.example.grocerypricelister;

import android.util.Log;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ControllerMaster{
    //constants
    private final static String TAG = "ControllerMaster";

    //properties
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    static MarketManager marketManager = new MarketManager();
    private ArrayList<Market> markets;
    private ArrayList<Product> products;

    public MarketManager getMarketManager(){
        return marketManager;
    }

    public ArrayList<Market> getMarkets(){
        Log.d(TAG, "getMarkets: ENTERED AND SENT MARKETS TO LOCATIONS");
        return markets;
    }

    public ArrayList<Product> getProducts(){ return products; }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }
}