package com.fiit.eatout.eatout.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.fiit.eatout.eatout.BackdropSetup;
import com.fiit.eatout.eatout.CartCardRecyclerViewAdapter;
import com.fiit.eatout.eatout.NavigationIconClickListener;
import com.fiit.eatout.eatout.ProductCardRecyclerViewAdapter;
import com.fiit.eatout.eatout.R;
import com.fiit.eatout.eatout.globalValues.Cart;
import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.ProductEntry;

public class CartFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.eout_cart_fragment, container, false);

        // Set up the tool bar
        setUpToolbar(view);

        // Set up the backdrop menu
        BackdropSetup.setup(view, getActivity());

        RecyclerView recyclerView = view.findViewById(R.id.cart_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        final CartCardRecyclerViewAdapter adapter = new CartCardRecyclerViewAdapter(
                Cart.getContents());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        if (global.orderCafe != null) toolbar.setTitle(global.orderCafe.title);
        else toolbar.setTitle("Корзина");

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
