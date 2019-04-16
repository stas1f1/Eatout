package com.fiit.eatout.eatout.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.fiit.eatout.eatout.NavigationHost;
import com.fiit.eatout.eatout.NavigationIconClickListener;
import com.fiit.eatout.eatout.CafeCardRecyclerViewAdapter;
import com.fiit.eatout.eatout.CafeGridItemDecoration;
import com.fiit.eatout.eatout.R;
import com.fiit.eatout.eatout.RecyclerItemClickListener;
import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.CafeEntry;

public class CafeGridFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.eout_cafe_grid_fragment, container, false);

        // Set up the tool bar
        setUpToolbar(view);

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.cafe_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        final CafeCardRecyclerViewAdapter adapter = new CafeCardRecyclerViewAdapter(
                CafeEntry.initCafeEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.eout_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.eout_product_grid_spacing_small);
        recyclerView.addItemDecoration(new CafeGridItemDecoration(largePadding, smallPadding));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        adapter.getDataByPosition(position);
                        Log.e("position", global.currentCafeid);
                        ((NavigationHost) getActivity()).navigateTo(new CafeFragment(), true); // Navigate to the next Fragment
                    }
                })
        );

        // Set up the backdrop buttons
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
                ((NavigationHost) getActivity()).navigateTo(new CartFragment(), true); // Navigate to the next Fragment
            }
        });
        closestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new CafeGridFragment(), true); // Navigate to the next Fragment
            }
        });
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new CartFragment(), true); // Navigate to the next Fragment
            }
        });
        salesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new SalesFragment(), true); // Navigate to the next Fragment
            }
        });
        favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new FavouritesFragment(), true); // Navigate to the next Fragment
            }
        });
        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new OrdersFragment(), true); // Navigate to the next Fragment
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new MapFragment(), true); // Navigate to the next Fragment
            }
        });
        preferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new PreferencesFragment(), true); // Navigate to the next Fragment
            }
        });
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new FeedbackFragment(), true); // Navigate to the next Fragment
            }
        });


        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setTitle(global.adress);

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.app_bar),
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.eout_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

}
