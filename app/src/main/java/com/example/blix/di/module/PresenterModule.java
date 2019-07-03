package com.example.blix.di.module;

import com.example.blix.api.ApiClient;
import com.example.blix.di.scope.SessionScope;
import com.example.blix.model.ResponseDiscover;
import com.example.blix.model.ResponseVideo;
import com.example.blix.ui.detail.DetailListener;
import com.example.blix.ui.detail.DetailPresenter;
import com.example.blix.ui.discover.DiscoverListener;
import com.example.blix.ui.discover.DiscoverPresenter;
import com.example.blix.ui.search.SearchListener;
import com.example.blix.ui.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @SessionScope
    ResponseDiscover providerItemsMedia() {
        return new ResponseDiscover();
    }

    @Provides
    @SessionScope
    DiscoverListener.Presenter providesPresenterDiscover(ResponseDiscover itemsMedia, ApiClient apiClient) {
        return new DiscoverPresenter(itemsMedia, apiClient);
    }

    @Provides
    @SessionScope
    SearchListener.Presenter providesPresenterSearch(ResponseDiscover itemsMedia, ApiClient apiClient) {
        return new SearchPresenter(itemsMedia, apiClient);
    }

    @Provides
    @SessionScope
    ResponseVideo providerItemsVideos() {
        return new ResponseVideo();
    }

    @Provides
    @SessionScope
    DetailListener.Presenter providesPresenterDetail(ResponseVideo itemsVideos, ApiClient apiClient) {
        return new DetailPresenter(itemsVideos, apiClient);
    }
}
