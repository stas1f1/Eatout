package com.fiit.eatout.eatout.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.fiit.eatout.eatout.BackdropSetup;
import com.fiit.eatout.eatout.NavigationHost;
import com.fiit.eatout.eatout.NavigationIconClickListener;
import com.fiit.eatout.eatout.ProductCardRecyclerViewAdapter;
import com.fiit.eatout.eatout.R;
import com.fiit.eatout.eatout.RecyclerItemClickListener;
import com.fiit.eatout.eatout.globalValues.Cart;
import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.ImageRequester;
import com.fiit.eatout.eatout.network.ProductEntry;

public class CafeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.eout_cafe_fragment, container, false);

        // Set up the tool bar
        setUpToolbar(view);

        // Set up the backdrop menu
        BackdropSetup.setup(view, getActivity());

        TextView TitleText = view.findViewById(R.id.cafe_menu_title);
        TitleText.setText(global.currentCafe.title);

        ImageRequester imageRequester;
        imageRequester = ImageRequester.getInstance();
        imageRequester.setImageFromUrl((NetworkImageView)view.findViewById(R.id.cafe_logo), global.currentCafe.url);

        final FloatingActionButton CartButton = view.findViewById(R.id.floating_action_button);
        CartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new CartFragment(), true); // Navigate to the next Fragment
            }
        });
        if (Cart.isEmpty())CartButton.hide();

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.product_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        final ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        /*
        int largePadding = getResources().getDimensionPixelSize(R.dimen.eout_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.eout_product_grid_spacing_small);
        recyclerView.addItemDecoration(new CafeGridItemDecoration(largePadding, smallPadding));
        */
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                      if (global.orderCafe == null)
                          global.orderCafe = global.currentCafe ;
                      Log.e("position", global.orderCafe.id);
                      if (global.orderCafe.id != global.currentCafe.id)
                          {
                              AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                              builder.setTitle("Очистить корзину?")
                                      .setMessage("При добавлении блюда из другого кафе корзина очистится")
                                      .setCancelable(false)
                                      .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                          public void onClick(DialogInterface dialog, int id) {
                                              dialog.cancel();
                                          }
                                      })
                                      .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                                          public void onClick(DialogInterface dialog, int id) {
                                              Cart.clear();
                                              global.orderCafe.copy(global.currentCafe);
                                              Cart.addEntry(adapter.getProductByPosition(position));
                                              ShowButton(CartButton);
                                              dialog.cancel();
                                          }
                                      });
                          }
                      else
                      {
                          Cart.addEntry(adapter.getProductByPosition(position));
                          ShowButton(CartButton);
                      }
                    }
                })
        );

        return view;
    }

    private void ShowButton(FloatingActionButton CartButton)
    {
        if (CartButton.isOrWillBeHidden())
        {
            CartButton.show();
            ValueAnimator fadeAnim = ObjectAnimator.ofFloat(CartButton, "alpha", 0f, 1f);
            fadeAnim.setDuration(250);
            fadeAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            fadeAnim.start();
        }
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setTitle("Ближайшие рестораны");

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
