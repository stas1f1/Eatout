package com.fiit.eatout.eatout.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.fiit.eatout.eatout.BackdropSetup;
import com.fiit.eatout.eatout.NavigationIconClickListener;
import com.fiit.eatout.eatout.R;

public class SalesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.eout_tmp_fragment, container, false);

        // Set up the tool bar
        setUpToolbar(view);

        // Set up the backdrop menu
        BackdropSetup.setup(view, getActivity());

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setTitle("Акции");

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
