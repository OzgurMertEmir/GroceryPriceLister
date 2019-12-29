package com.example.grocerypricelister;

public class Product {

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
    public double getPrice(){
        return price;
    }

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
        favorites = a;
    }

    public void setSale(boolean a){
        sale = a;
    }

    public void lifespanDailyUpdate(){
        lifespan --;
    }
    public void setAvailable(){
        if (getStock() > 0){
            available = true;
        }
        else{
            available = false;
        }
    }

}
