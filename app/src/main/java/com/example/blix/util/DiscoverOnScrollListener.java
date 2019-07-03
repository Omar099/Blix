package com.example.blix.util;

import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blix.adapter.ItemMediaAdapterListener;

public class DiscoverOnScrollListener extends RecyclerView.OnScrollListener {
    private ItemMediaAdapterListener.View view;
    private GridLayoutManager layoutManager;
    //Vars control scroll
    private int previousTotal = 0;
    private boolean loading = true;

    public DiscoverOnScrollListener(ItemMediaAdapterListener.View view, GridLayoutManager layoutManager) {
        this.view = view;
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        loading = newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading && (totalItemCount > previousTotal)) {
            loading = false;
            previousTotal = totalItemCount;

        }

        int visibleThreshold = 15;
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            view.setDiscoverRequest();
            loading = true;
        }
    }
}
