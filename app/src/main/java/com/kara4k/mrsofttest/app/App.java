package com.kara4k.mrsofttest.app;


import android.app.Application;

import com.kara4k.mrsofttest.di.AppComponent;
import com.kara4k.mrsofttest.di.DaggerAppComponent;
import com.kara4k.mrsofttest.di.modules.AppModule;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
