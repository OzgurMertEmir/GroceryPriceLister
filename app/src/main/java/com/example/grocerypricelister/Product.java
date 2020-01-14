package com.example.grocerypricelister;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Product {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    double price;
    int stock;
    int weight;
    String name;
    boolean available;
    boolean favorites;
    boolean sale;
    int lifespan;
    String detailed_info;

    public Product(){
        //Data fetchimg
    }

    //methods
    public double getPrice(){ return price; }

    public boolean getFavorites(){
        return favorites;
    }

    public boolean getSale(){
        return sale;
    }

    public boolean getAvailable(){
        return available;
    }

    public int getLifespan(){
        return lifespan;
    }

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public int getStock(){
        return stock;
    }

    public void setFavorites(boolean a){
        this.favorites = a;
    }

    public void setSale(boolean a){
        this.sale = a;
    }

    public void lifespanDailyUpdate(){
        lifespan --;
    }

    public void setAvailable(){
        if (this.getStock() > 0){
            this.available = true;
        }
        else{
            this.available = false;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.propertyChangeSupport.addPropertyChangeListener("stock", listener);
    }
}
