package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView productImage;
    public TextView productTitle;
    public TextView productRating;
    public TextView productDistance;
    public TextView productCategories;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productRating = itemView.findViewById(R.id.product_rating);
        productDistance = itemView.findViewById(R.id.product_distance);
        productCategories = itemView.findViewById(R.id.product_categories);
    }
}
