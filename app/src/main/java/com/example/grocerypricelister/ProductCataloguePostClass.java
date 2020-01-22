package com.example.grocerypricelister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductCataloguePostClass extends RecyclerView.Adapter<ProductCataloguePostClass.ViewHolder> {

    private final Context context;
    private ControllerMaster controllerMaster;
    private ArrayList<Product> products;

    public ProductCataloguePostClass(ArrayList<Product> products, Context context) {

        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.gpNameText.setText(product.getName());
        holder.gpSizeText.setText(product.getWeight());
        holder.gpPriceText.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView gpNameText;
        public TextView gpSizeText;
        public TextView gpPriceText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gpNameText = itemView.findViewById(R.id.gpName);
            gpSizeText = itemView.findViewById(R.id.gpSize);
            gpPriceText = itemView.findViewById(R.id.gpPrice);
        }
    }
}