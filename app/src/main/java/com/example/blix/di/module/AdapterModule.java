package com.example.blix.di.module;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.blix.R;
import com.example.blix.adapter.ItemMediaAdapter;
import com.example.blix.adapter.SearchMediaAdapter;
import com.example.blix.di.scope.SessionScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {
    @Provides
    @SessionScope
    RequestManager initGlide(Context context) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.item_blank_state)
                .error(R.drawable.item_error_state);

        return Glide.with(context)
                .setDefaultRequestOptions(options);
    }

    @SessionScope
    @Provides
    @Named("discoverAdapter")
    ItemMediaAdapter provideDiscoverAdapter(RequestManager requestManager) {
        return new ItemMediaAdapter(requestManager);
    }

    @SessionScope
    @Provides
    @Named("popularAdapter")
    ItemMediaAdapter providePopularAdapter(RequestManager requestManager) {
        return new ItemMediaAdapter(requestManager);
    }

    @SessionScope
    @Provides
    @Named("topRatedAdapter")
    ItemMediaAdapter providesTopRatedAdapter(RequestManager requestManager) {
        return new ItemMediaAdapter(requestManager);
    }

    @SessionScope
    @Provides
    @Named("upcomingAdapter")
    ItemMediaAdapter providesUpcomingAdapter(RequestManager requestManager) {
        return new ItemMediaAdapter(requestManager);
    }

    @SessionScope
    @Provides
    @Named("searchAdapter")
    SearchMediaAdapter providesSearchAdapter(RequestManager requestManager) {
        return new SearchMediaAdapter(requestManager);
    }
}