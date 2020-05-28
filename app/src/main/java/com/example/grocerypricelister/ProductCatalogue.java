package com.example.grocerypricelister;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ProductCatalogue extends AppCompatActivity {

    ControllerMaster controllerMaster;
    ArrayList<String> gpName;
    ArrayList<String> gpSize;
    ArrayList<String> gpPrice;
    ArrayList<Boolean> gpCondition;
    ArrayList<Product> products;
    ArrayList<Integer> inBasketNum;
    ProductCataloguePostClass adapter;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_catalogue);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        controllerMaster = new ControllerMaster();
        products = controllerMaster.getProducts();
        gpName = new ArrayList<>();
        gpSize = new ArrayList<>();
        gpPrice = new ArrayList<>();
        gpCondition = new ArrayList<>();
        inBasketNum = new ArrayList<>();
        for (Product product : products) {
            gpName.add(product.getName());
            gpSize.add(String.valueOf(product.getWeight()));
            gpPrice.add(String.valueOf(product.getPrice()));
            gpCondition.add(product.getAvailable());
            inBasketNum.add(product.getinBasket());
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
        adapter.setOnItemClickListener(new ProductCataloguePostClass.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Product p = products.get(position);
                Toast.makeText(getApplicationContext(), "clicked to " + p.getName(), Toast.LENGTH_SHORT).show();
                //urun arayuzune gecicek burada
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
