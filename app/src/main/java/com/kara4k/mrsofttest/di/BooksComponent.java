package com.kara4k.mrsofttest.di;

import com.kara4k.mrsofttest.di.modules.BooksModule;
import com.kara4k.mrsofttest.di.scopes.PerActivity;
import com.kara4k.mrsofttest.view.BooksActivity;

import dagger.Component;

@PerActivity
@Component(modules = BooksModule.class, dependencies = AppComponent.class)
public interface BooksComponent {

    void injectBooksActivity(BooksActivity activity);
}
