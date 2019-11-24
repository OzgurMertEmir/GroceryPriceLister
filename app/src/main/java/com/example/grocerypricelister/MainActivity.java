package com.example.grocerypricelister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void seeTheCatalogue(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ProductCatalogue.class);
        startActivity(intent);
    }

    protected void seeTheSearchBar (View view)
    {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivity(intent);
    }
}
