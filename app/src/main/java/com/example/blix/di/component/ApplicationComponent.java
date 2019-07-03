package com.example.blix.di.component;

import com.example.blix.di.module.ApplicationContextModule;
import com.example.blix.di.module.PresenterModule;
import com.example.blix.di.module.RetrofitModule;
import com.example.blix.di.scope.ApplicationScope;
import com.example.blix.ui.MainActivity;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    PresenterSubComponent plusPresenterSubComponent(PresenterModule presenterModule);
}
