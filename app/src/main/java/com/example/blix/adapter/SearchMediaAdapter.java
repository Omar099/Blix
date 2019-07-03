package com.example.blix.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.blix.R;
import com.example.blix.adapterViewHolder.ItemMediaViewHolder;
import com.example.blix.model.ItemMedia;

import java.util.ArrayList;
import java.util.List;

public class SearchMediaAdapter extends RecyclerView.Adapter<ItemMediaViewHolder> {
    private int side;
    private RequestManager requestManager;
    private List<ItemMedia> itemMedias = new ArrayList<>();
    private SearchMediaAdapterListener.View view;

    public SearchMediaAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public ItemMediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_movie_cover, parent, false);
        return new ItemMediaViewHolder(view, requestManager);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMediaViewHolder holder, int pos) {
        final ItemMedia localItem = itemMedias.get(pos);

        holder.bind(localItem);
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = (getSide() * 14 / 10);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setItemOnClick(localItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != itemMedias ? itemMedias.size() : 0;
    }

    public void setView(SearchMediaAdapterListener.View view, int sideX) {
        this.view = view;
        this.side = sideX / 4;
    }

    public void setView(SearchMediaAdapterListener.View view, int sideX, int colums) {
        this.view = view;
        this.side = sideX / colums;
    }

    private int getSide() {
        return this.side;
    }

    public void setData(List<ItemMedia> itemMedias) {
        this.itemMedias.addAll(itemMedias);
        notifyDataSetChanged();
    }

    public void setDataFilter(String query) {
        if (!query.isEmpty()) {
            List<ItemMedia> itemTemps = getFilterOutput(itemMedias, query);
            if (itemTemps.size() > 0) {
                clearList();
                this.itemMedias.addAll(itemTemps);
                notifyDataSetChanged();
            }
        }
    }

    private static List<ItemMedia> getFilterOutput(List<ItemMedia> itemMediaList, String filter) {
        List<ItemMedia> result = new ArrayList<>();
        for (ItemMedia item : itemMediaList) {
            if (item.getTitle().toLowerCase().contains(filter.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    public void clearList() {
        if (itemMedias != null) {
            itemMedias.clear();
        }
    }
}
