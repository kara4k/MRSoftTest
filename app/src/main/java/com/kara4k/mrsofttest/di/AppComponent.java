package com.kara4k.mrsofttest.di;


import android.content.Context;

import com.kara4k.mrsofttest.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context shareContext();
}
