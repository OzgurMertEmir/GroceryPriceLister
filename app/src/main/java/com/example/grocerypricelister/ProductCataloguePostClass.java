package com.example.grocerypricelister;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductCataloguePostClass extends ArrayAdapter<String> {

    private ArrayList<String> gpName;
    private ArrayList<String> gpPrice;
    private ArrayList<String> gpSize;
    private ArrayList<Boolean> gpCondition;
    private final Activity context;

    public ProductCataloguePostClass(ArrayList<String> gpName, ArrayList<String> gpPrice, ArrayList<String> gpSize, ArrayList<Boolean> gpCondition, Activity context) {

        super(context, R.layout.product_item, gpName);
        this.gpName = gpName;
        this.gpPrice = gpPrice;
        this.gpSize = gpSize;
        this.gpCondition = gpCondition;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View groceryInfo = layoutInflater.inflate(R.layout.product_item, null, true);

        //Find components
        TextView gpNameText = groceryInfo.findViewById(R.id.gpName);
        TextView gpSizeText = groceryInfo.findViewById(R.id.gpSize);
        TextView gpPriceText = groceryInfo.findViewById(R.id.gpPrice);

        //set method  of components
        gpNameText.setText(gpName.get(position));
        gpSizeText.setText(gpSize.get(position));
        gpPriceText.setText(gpPrice.get(position));

        return groceryInfo;
    }
}