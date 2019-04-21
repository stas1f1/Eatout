package com.fiit.eatout.eatout.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.fiit.eatout.eatout.BackdropSetup;
import com.fiit.eatout.eatout.CartCardRecyclerViewAdapter;
import com.fiit.eatout.eatout.NavigationHost;
import com.fiit.eatout.eatout.NavigationIconClickListener;
import com.fiit.eatout.eatout.ProductCardRecyclerViewAdapter;
import com.fiit.eatout.eatout.R;
import com.fiit.eatout.eatout.SwipeController;
import com.fiit.eatout.eatout.SwipeControllerActions;
import com.fiit.eatout.eatout.globalValues.Cart;
import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.ProductEntry;
import com.fiit.eatout.eatout.network.SQLPlaceOrder;

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
        final View view = inflater.inflate(R.layout.eout_cart_fragment, container, false);

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

        setTotal(view);

        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                adapter.removeEntry(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                setTotal(view);
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        final MaterialButton OrderButton = view.findViewById(R.id.order_button);
        OrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLPlaceOrder order = new SQLPlaceOrder();
                order.start(global.userID, global.orderCafe.id, Cart.toJSON(), "00:02:00");
                try {
                    order.join();
                } catch (InterruptedException ie) {
                    Log.e("pass 0", ie.getMessage());
                }
                String orderID = order.getOrderID();
                if (orderID != "-1") {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Заказ успешно оформлен")
                            .setMessage("Номер вашего заказа: " + orderID +". Предьявите его на кассе, чтобы получить заказ.")
                            .setCancelable(false)
                            .setPositiveButton("Далее", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Cart.clear();
                                    ((NavigationHost) getActivity()).navigateTo(new OrdersFragment(), false);
                                    dialog.cancel();
                                }
                            });
                    builder.show();
                    }

                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Ошибка оформления заказа")
                            .setMessage("Проверьте ваше интернет-соединение и повторите попытку позже")
                            .setCancelable(false)
                            .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder.show();
                }
            }
        });
        if (Cart.isEmpty())OrderButton.setVisibility(View.INVISIBLE);


        return view;
    }

    private void setTotal(View view)
    {
        TextView total = view.findViewById(R.id.cart_total);
        String stotal = Cart.getPrice() + " \u20BD";
        total.setText(stotal);
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
