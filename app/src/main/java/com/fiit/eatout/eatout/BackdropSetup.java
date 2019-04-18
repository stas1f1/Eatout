package com.fiit.eatout.eatout;

import android.support.design.button.MaterialButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.fiit.eatout.eatout.fragments.CafeGridFragment;
import com.fiit.eatout.eatout.fragments.CartFragment;
import com.fiit.eatout.eatout.fragments.CategoriesFragment;
import com.fiit.eatout.eatout.fragments.FavouritesFragment;
import com.fiit.eatout.eatout.fragments.FeedbackFragment;
import com.fiit.eatout.eatout.fragments.MapFragment;
import com.fiit.eatout.eatout.fragments.OrdersFragment;
import com.fiit.eatout.eatout.fragments.PreferencesFragment;
import com.fiit.eatout.eatout.fragments.SalesFragment;

/**
 * Created by C on 18.04.2019.
 */

public class BackdropSetup {
    public static void setup(View view, final FragmentActivity activity){
        MaterialButton cartButton = view.findViewById(R.id.backdrop_cart_button);
        MaterialButton closestButton = view.findViewById(R.id.backdrop_closest_button);
        MaterialButton categoriesButton = view.findViewById(R.id.backdrop_categories_button);
        MaterialButton salesButton = view.findViewById(R.id.backdrop_sales_button);
        MaterialButton favouritesButton = view.findViewById(R.id.backdrop_favourites_button);
        MaterialButton ordersButton = view.findViewById(R.id.backdrop_orders_button);
        MaterialButton mapButton = view.findViewById(R.id.backdrop_map_button);
        MaterialButton preferencesButton = view.findViewById(R.id.backdrop_preferences_button);
        MaterialButton feedbackButton = view.findViewById(R.id.backdrop_feedback_button);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new CartFragment(), true); // Navigate to the next Fragment
            }
        });
        closestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new CafeGridFragment(), true); // Navigate to the next Fragment
            }
        });
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new CategoriesFragment(), true); // Navigate to the next Fragment
            }
        });
        salesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new SalesFragment(), true); // Navigate to the next Fragment
            }
        });
        favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new FavouritesFragment(), true); // Navigate to the next Fragment
            }
        });
        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new OrdersFragment(), true); // Navigate to the next Fragment
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new MapFragment(), true); // Navigate to the next Fragment
            }
        });
        preferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new PreferencesFragment(), true); // Navigate to the next Fragment
            }
        });
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) activity).navigateTo(new FeedbackFragment(), true); // Navigate to the next Fragment
            }
        });
    }
}
