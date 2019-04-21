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
public class ProductEntry {
    private static final String TAG = ProductEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String ID;
    public final String url;
    public final String weight;
    public final String time;
    public final String price;
    public final String description;

    public ProductEntry(
            String title, String ID, String dynamicUrl, String url, String weight, String time, String price, String description) {
        this.ID = ID;
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
        this.weight = weight + "г";
        this.time = ConvertTime(time);
        this.price = price + "р.";
        this.description = description;
    }

    private String ConvertTime(String secs)
    {
        double mins = Double.parseDouble(secs) / 60;
        String suffix;
        int prelastDigit = ((int)Math.floor(mins) % 100)/10;
        int lastDigit = (int)Math.floor(mins) % 10;

        if (prelastDigit == 1 || lastDigit > 4) suffix = " минут";
        else if (lastDigit == 1) suffix = " минутa";
        else suffix = " минуты";

        return String.valueOf(mins) + suffix;
    }

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of CafeEntry objects
     */
    public static List<ProductEntry> initProductEntryList(Resources resources) {

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
        Type productListType = new TypeToken<ArrayList<ProductEntry>>() {
        }.getType();
        return gson.fromJson(jsonProductString, productListType);
    }
}