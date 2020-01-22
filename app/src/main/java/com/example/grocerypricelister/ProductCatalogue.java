package com.example.grocerypricelister;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ProductCatalogue extends AppCompatActivity {

    TableLayout productCatalogue;
    ControllerMaster controllerMaster;
    ArrayList<String> gpName;
    ArrayList<String> gpSize;
    ArrayList<String> gpPrice;
    ArrayList<Boolean> gpCondition;
    ArrayList<Product> products;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_catalogue);
        searchView = findViewById(R.id.searchView);
        productCatalogue = findViewById(R.id.tableLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        controllerMaster = new ControllerMaster();
        products = controllerMaster.getProducts();
        gpName = new ArrayList<>();
        gpSize = new ArrayList<>();
        gpPrice = new ArrayList<>();
        gpCondition = new ArrayList<>();
        for (Product product : products) {
            gpName.add(product.getName());
            gpSize.add(String.valueOf(product.getWeight()));
            gpPrice.add(String.valueOf(product.getPrice()));
            gpCondition.add(product.getAvailable());
        }

        adapter = new ProductCataloguePostClass(products, this);

        recyclerView.setAdapter(adapter);

        controllerMaster.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    gpName.clear();
                    gpPrice.clear();
                    gpSize.clear();
                    gpCondition.clear();

                    products = controllerMaster.getProducts();

                    for(Product product : products){
                        gpName.add(product.getName());
                        gpPrice.add(String.valueOf(product.getPrice()));
                        gpSize.add(String.valueOf(product.getWeight()));
                        adapter.notifyDataSetChanged();
                    }
                }catch( Exception e ){
                    e.printStackTrace();
                }
            }
        });
        ///recyclerView a clickListener eklencek

    }
}
