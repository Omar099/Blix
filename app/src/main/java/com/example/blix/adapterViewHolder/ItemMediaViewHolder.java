package com.example.blix.adapterViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.blix.R;
import com.example.blix.model.ItemMedia;

import static com.example.blix.api.ApiConstants.BASE_IMG;

public class ItemMediaViewHolder extends RecyclerView.ViewHolder {
    private RequestManager requestManager;
    private LinearLayout itemMovieCover;
    private ImageView ivImageUrl;

    public ItemMediaViewHolder(@NonNull View itemView, RequestManager requestManager) {
        super(itemView);
        this.requestManager = requestManager;
        ivImageUrl = itemView.findViewById(R.id.iv_image_url);
        itemMovieCover = itemView.findViewById(R.id.item_movie_cover);
    }

    public void bind(ItemMedia ItemMedia) {
        String url = BASE_IMG + ItemMedia.getPosterPath();
        requestManager
                .load(url)
                .into(ivImageUrl);
    }
}
