package com.example.grocerypricelister;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

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
    int inBasket;
    ArrayList<Market> sellers;
    String detailed_info;

    /**

    public Product(HashMap<String, String > properties){
        //Data fetchimg
        name = properties.get("name");
        price = Double.valueOf(properties.get("price"));
        stock = Integer.valueOf(properties.get("stock"));
        weight = Integer.valueOf(properties.get("size"));
        if(stock > 0){
            available = true;
        }
        else{
            available = false;
        }
    }
     */
    public Product(String name, double price, int weight, int stock){
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.stock = stock;
        this.inBasket = 0;
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
        if(lifespan > 0){
            lifespan --;
        }
    }

    public void setAvailable(){
        if (this.getStock() > 0){
            this.available = true;
        }
        else{
            this.available = false;
        }
    }

    public int getinBasket(){ return inBasket; }

    public void addToBasket(){ inBasket++; }

    public void remFromBasket(){
        if(inBasket > 0){
            inBasket--;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.propertyChangeSupport.addPropertyChangeListener("stock", listener);
    }
}
