package com.example.grocerypricelister;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MarketManager {
    //constants
    private final static String TAG = "MarketManager";

    //properties
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private DataAccess dataAccess;
    private ArrayList<Market> markets;
    private static Market chosenMarket;


    //constructors
    public MarketManager() {
        dataAccess = new DataAccess();
        markets = (ArrayList<Market>) dataAccess.getMarketler();
        dataAccess.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ArrayList<Market> oldMarkets = markets;
                Log.d(TAG, "propertyChange: " + evt.getPropertyName() + " Old Value: " + evt.getOldValue() + "New Value: " + evt.getNewValue());
                markets = dataAccess.getMarketler();
                try {
                    pcs.firePropertyChange(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        chosenMarket = null;
    }

    //methods
    public Market chooseMarket(String s){
        for(Market market : markets){
            if(market.getName().equalsIgnoreCase(s)){
                chosenMarket = market;
            }
        }
        return chosenMarket;
    }

    public Market getChosenMarket(){
        return chosenMarket;
    }

    public String getChosenMarketName(){
        if(chosenMarket != null){
            return chosenMarket.getName();
        }else {
            return "No Market chosen";
        }
    }

    public String getChosenMarketAddress(){
        if (chosenMarket != null){
            return chosenMarket.getAdress();
        }else{
            return "No Market chosen";
        }
    }

    public LatLng getChosenMarketLocation(){
        if (chosenMarket != null){
            return chosenMarket.getLocation();
        }else{
            return new LatLng(0,0);
        }
    }

    public boolean getChosenMarketisOpen(){
        if (chosenMarket != null){
            return chosenMarket.isOpen();
        }
        else{
            return false;
        }
    }

    public void removeChosenMarket(){
        chosenMarket = null;
    }

    public ArrayList<Market> getMarkets(){
        Log.d(TAG, "getMarkets: Method entered!!!!!");
        for(Market market : markets){
            System.out.println( "---------------------------------------------------------------------" + market.getName() + "--------------------------------------------------------------------------------");
        }
        return markets;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }
}
