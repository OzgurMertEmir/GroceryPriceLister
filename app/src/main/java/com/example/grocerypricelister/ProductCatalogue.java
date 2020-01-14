package com.example.grocerypricelister;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TableLayout;

public class ProductCatalogue extends AppCompatActivity {

    TableLayout productCatalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_catalogue);
        productCatalogue = findViewById(R.id.tableLayout);
    }
}
