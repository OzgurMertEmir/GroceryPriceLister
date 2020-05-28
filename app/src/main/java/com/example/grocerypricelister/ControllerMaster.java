package com.example.grocerypricelister;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

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
    private ArrayList<Product> products = new ArrayList<Product>();

    public MarketManager getMarketManager(){
        return marketManager;
    }

    public ArrayList<Market> getMarkets(){
        Log.d(TAG, "getMarkets: ENTERED AND SENT MARKETS TO LOCATIONS");
        return markets;
    }

    public ArrayList<Product> getProducts(){
        //silincek bunlar deneme icin varlar
        Product product1 = new Product("Eti Popkek", 2, 200, 20);
        Product product2 = new Product("Eti Gofret", 1, 100, 1);
        Product product3 = new Product("Biskrem", 2.5, 150, 0);
        Product product4 = new Product("Tutku", 2, 300, 10);
        Product product5 = new Product("Damak", 6, 250, 50);
        Product product6 = new Product("Jelibon", 4, 80, 5);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        return products; }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }
}