package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class OrderCardViewHolder extends RecyclerView.ViewHolder {

    TextView CafeTitle;
    RecyclerView Contents;
    TextView Time;
    TextView OrderID;

    public OrderCardViewHolder(@NonNull View itemView) {
        super(itemView);

        CafeTitle = itemView.findViewById(R.id.cafe_title);
        Contents = itemView.findViewById(R.id.orders_recycler_view);
        Time = itemView.findViewById(R.id.order_time);
        OrderID = itemView.findViewById(R.id.order_ID);
    }


}
