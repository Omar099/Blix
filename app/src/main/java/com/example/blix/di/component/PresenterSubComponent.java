package com.example.blix.di.component;

import com.example.blix.di.module.AdapterModule;
import com.example.blix.di.module.PresenterModule;
import com.example.blix.di.scope.ApplicationScope;
import com.example.blix.di.scope.SessionScope;
import com.example.blix.ui.detail.DetailActivity;
import com.example.blix.ui.discover.DiscoverFragment;
import com.example.blix.ui.search.SearchFragment;
import com.example.blix.ui.search.SearchListener;

import dagger.Subcomponent;

@SessionScope
@Subcomponent(modules = {PresenterModule.class, AdapterModule.class})
public interface PresenterSubComponent {
    void inject(DiscoverFragment discoverFragment);

    void inject(DetailActivity detailActivity);

    void inject(SearchFragment searchFragment);
}
