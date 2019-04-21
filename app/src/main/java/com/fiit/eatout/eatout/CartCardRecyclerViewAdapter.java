package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiit.eatout.eatout.ProductCardViewHolder;
import com.fiit.eatout.eatout.R;
import com.fiit.eatout.eatout.globalValues.Cart;
import com.fiit.eatout.eatout.network.ImageRequester;
import com.fiit.eatout.eatout.network.ProductEntry;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class CartCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<ProductEntry> productList;
    private ImageRequester imageRequester;

    public CartCardRecyclerViewAdapter(List<ProductEntry> productList) {
        this.productList = productList;
    }

    public void removeEntry(int position)
    {
        productList.remove(position);
        Cart.removeEntry(position);
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eout_cart_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            String price = product.price + " \u20BD";
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(price);
        }
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
