package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView cafeImage;
    public TextView cafeTitle;
    public TextView cafeRating;
    public TextView cafeDistance;
    public TextView cafeCategories;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        cafeImage = itemView.findViewById(R.id.product_image);
        cafeTitle = itemView.findViewById(R.id.product_title);
        cafeRating = itemView.findViewById(R.id.product_rating);
        cafeDistance = itemView.findViewById(R.id.product_distance);
        cafeCategories = itemView.findViewById(R.id.product_categories);
    }


}
