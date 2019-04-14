package com.fiit.eatout.eatout.network;


import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.fiit.eatout.eatout.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A cafe entry in the list of cafes.
 */
public class CafeEntry {
    private static final String TAG = CafeEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String url;
    public final String rating;
    public final String distance;
    public final String categories;

    public CafeEntry(
            String title, String dynamicUrl, String url, String rating, String distance, String categories) {
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
        this.rating = rating;
        this.distance = distance;
        this.categories = categories;
    }

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of CafeEntry objects
     */
    public static List<CafeEntry> initCafeEntryList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.restaurants);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonCafeString = writer.toString();
        Gson gson = new Gson();
        Type cafeListType = new TypeToken<ArrayList<CafeEntry>>() {
        }.getType();
        return gson.fromJson(jsonCafeString, cafeListType);
    }
}