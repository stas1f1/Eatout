package com.fiit.eatout.eatout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.CafeEntry;
import com.fiit.eatout.eatout.network.ImageRequester;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class CafeCardRecyclerViewAdapter extends RecyclerView.Adapter<CafeCardViewHolder> {

    private List<CafeEntry> cafeList;
    private ImageRequester imageRequester;

    public CafeCardRecyclerViewAdapter(List<CafeEntry> productList) {
        this.cafeList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public CafeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eout_cafe_card, parent, false);
        return new CafeCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CafeCardViewHolder holder, int position) {
        if (cafeList != null && position < cafeList.size()) {
            CafeEntry product = cafeList.get(position);

            holder.cafeTitle.setText(product.title);
            holder.cafeRating.setText(product.rating);
            holder.cafeDistance.setText(product.distance);
            holder.cafeCategories.setText(product.categories);
            imageRequester.setImageFromUrl(holder.cafeImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    public void getDataByPosition(int position)
    {
        CafeEntry cutrrentCafe = cafeList.get(position);
        global.currentCafeid = cutrrentCafe.id;
        global.currentCafeURL = cutrrentCafe.url;
        global.currentCafeTitle = cutrrentCafe.title;
    }

}
