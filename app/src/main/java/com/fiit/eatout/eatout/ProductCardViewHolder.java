package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView productImage;
    public TextView productTitle;
    public String productID;
    public TextView productWeight;
    public TextView productTime;
    public TextView productPrice;
    public TextView productDescription;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productWeight = itemView.findViewById(R.id.product_weight);
        productTime = itemView.findViewById(R.id.product_time);
        productPrice = itemView.findViewById(R.id.product_price);
        productDescription = itemView.findViewById(R.id.product_description);
    }


}
