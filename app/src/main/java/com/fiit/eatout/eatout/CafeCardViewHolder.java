package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class CafeCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView cafeImage;
    public TextView cafeTitle;
    public TextView cafeRating;
    public TextView cafeDistance;
    public TextView cafeCategories;

    public CafeCardViewHolder(@NonNull View itemView) {
        super(itemView);
        cafeImage = itemView.findViewById(R.id.cafe_image);
        cafeTitle = itemView.findViewById(R.id.cafe_title);
        cafeRating = itemView.findViewById(R.id.cafe_rating);
        cafeDistance = itemView.findViewById(R.id.cafe_distance);
        cafeCategories = itemView.findViewById(R.id.cafe_categories);
    }


}
