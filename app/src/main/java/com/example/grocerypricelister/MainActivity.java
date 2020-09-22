package com.example.grocerypricelister;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MarketPostClass adapter;
    private FloatingActionButton basketButton;
    ControllerMaster controllerMaster;
    ArrayList<Market> markets;
    ArrayList mName;
    ArrayList mAddress;
    ArrayList mCondition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.marketsList);
        controllerMaster = new ControllerMaster();
        markets = controllerMaster.getMarkets();
        mName = new ArrayList<>();
        mAddress = new ArrayList<>();
        mCondition = new ArrayList<>();
        basketButton = findViewById(R.id.basket);
        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });
        for(Market market: markets){
            mName.add(market.getName());
            mAddress.add(market.getAdress());
            mCondition.add(market.isOpen());
        }
        adapter = new MarketPostClass(mName, mAddress, mCondition, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                controllerMaster.getMarketManager().chooseMarket(markets.get(position).getName());
                //startService();
                Intent intent = new Intent(getApplicationContext(), ProductCatalogue.class);
                startActivity(intent);
            }
        });
    }

    public void seeTheCatalogue(View view){
        Intent intent = new Intent(getApplicationContext(), ProductCatalogue.class);
        startActivity(intent);
    }

    public void seeTheMap(View view){
        Intent intent = new Intent(getApplicationContext(), Maps.class);
        startActivity(intent);
    }
}
