package com.example.grocerypricelister;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductCataloguePostClass extends RecyclerView.Adapter<ProductCataloguePostClass.ViewHolder> implements Filterable {

    private final Context context;
    private ArrayList<Product> products;
    ArrayList<Product> productsFull;
    private OnItemClickListener onItemClickListener;
    public Button remove;
    public Button add;
    static SharedPreferences numOfProducts;
    Set<String> spData;
    static ArrayList<Integer> pNumInBasket;
    SharedPreferences.Editor editor;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ProductCataloguePostClass(ArrayList<Product> products, Context context) {

        this.products = products;
        productsFull = new ArrayList<>(products);
        this.context = context;
        pNumInBasket = new ArrayList<>();
        numOfProducts = context.getSharedPreferences("com.example.grocerypricelister", Context.MODE_PRIVATE);
        spData = new HashSet<>(numOfProducts.getStringSet("numOfinBasket", new HashSet<String>()));
        for(Product p : products){
            spData.add(String.valueOf(p.getinBasket()));
            pNumInBasket.add(p.getinBasket());
        }

        editor = numOfProducts.edit();
        editor.putStringSet("numOfinBasket", spData).apply();
        editor.commit();
        System.out.println("------------------------------------------------------------------------------------"+ pNumInBasket);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = products.get(position);
        holder.gpNameText.setText(product.getName());
        holder.gpSizeText.setText(String.valueOf(product.getWeight()));
        holder.gpPriceText.setText(String.valueOf(product.getPrice()));
        holder.itemnumberinBasket.setText(String.valueOf(pNumInBasket.get(position)));
        View.OnClickListener buttonClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.remove:
                        int indexR = holder.getAdapterPosition();
                        Product pR = products.get(indexR);
                        pR.remFromBasket();
                        pNumInBasket.set(indexR, pR.getinBasket());
                        pR.setinBasket(pR.getinBasket());
                        editor.clear();
                        editor.putStringSet("numOfinBasket", spData).apply();
                        editor.commit();
                        notifyDataSetChanged();
                        break;

                    case R.id.add:
                        int indexA= holder.getAdapterPosition();
                        Product pA = products.get(indexA);
                        pA.addToBasket();
                        pNumInBasket.set(indexA, pA.getinBasket());
                        pA.setinBasket(pA.getinBasket());
                        editor.clear();
                        editor.putStringSet("numOfinBasket", spData).apply();
                        editor.commit();
                        notifyDataSetChanged();
                        break;
                }
            }
        };
        remove.setOnClickListener(buttonClickListener);
        add.setOnClickListener(buttonClickListener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filteredProducts = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredProducts.addAll(productsFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Product product : productsFull){
                    if(product.getName().toLowerCase().contains(filterPattern)){
                        filteredProducts.add(product);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredProducts;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView gpNameText;
        public TextView gpSizeText;
        public TextView gpPriceText;
        public TextView itemnumberinBasket;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            gpNameText = itemView.findViewById(R.id.gpName);
            gpSizeText = itemView.findViewById(R.id.gpSize);
            gpPriceText = itemView.findViewById(R.id.gpPrice);
            itemnumberinBasket = itemView.findViewById(R.id.numOfProductsinBasket);
            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}