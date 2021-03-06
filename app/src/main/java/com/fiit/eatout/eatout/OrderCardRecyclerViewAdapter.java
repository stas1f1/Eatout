package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiit.eatout.eatout.network.ImageRequester;
import com.fiit.eatout.eatout.network.ProductEntry;

import java.util.List;
import java.util.Locale;

/**
 * Adapter used to show a simple grid of products.
 */
public class OrderCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<ProductEntry> productList;
    private ImageRequester imageRequester;

    public OrderCardRecyclerViewAdapter(List<ProductEntry> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eout_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            String weight = product.weight + "г";
            String price = product.price + "\u20BD";
            String time = fmt(Double.parseDouble(product.time) / 60) + " минут";
            holder.productTitle.setText(product.title);
            holder.productWeight.setText(weight);
            holder.productTime.setText(time);
            holder.productPrice.setText(price);
            holder.productDescription.setText(product.description);
            Log.e("URL:",product.url);
            imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%s",(long)d);
        else
            return String.format(Locale.getDefault(),"%.1f",d);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public ProductEntry getProductByPosition(int position)
    {
        return productList.get(position);
    }
}
