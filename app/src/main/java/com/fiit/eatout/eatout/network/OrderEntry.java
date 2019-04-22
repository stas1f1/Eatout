package com.fiit.eatout.eatout.network;


import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.fiit.eatout.eatout.globalValues.global;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A product entry in the list of products
 */
public class OrderEntry {
    private static final String TAG = OrderEntry.class.getSimpleName();

    public final String orderID;
    public final String cafeID;
    public final String time;
    public final String timer;
    public final String contents;

    public OrderEntry(
            String orderID,
            String cafeID,
            String time,
            String timer,
            String contents) {

        this.orderID = orderID;
        this.cafeID = cafeID;
        this.time = time;
        this.timer = timer;
        this.contents = contents;
    }

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of CafeEntry objects
     */
    public static List<OrderEntry> initProductEntryList(Resources resources) {

        SQLGetProducts GetProducts;
        GetProducts = new SQLGetProducts();
        GetProducts.start(global.currentCafe.id);

        try {
            GetProducts.join();
        }
        catch (InterruptedException ie)
        {
            Log.e("products loading error", ie.getMessage());
        }

        String jsonProductString;
        jsonProductString = GetProducts.result;
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<OrderEntry>>() {
        }.getType();
        return gson.fromJson(jsonProductString, productListType);
    }
}