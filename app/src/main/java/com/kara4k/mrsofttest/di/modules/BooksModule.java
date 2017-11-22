package com.kara4k.mrsofttest.di.modules;

import com.kara4k.mrsofttest.di.scopes.PerActivity;
import com.kara4k.mrsofttest.view.BooksView;

import dagger.Module;
import dagger.Provides;

@Module
public class BooksModule {

    private BooksView mBooksView;

    public BooksModule(BooksView booksView) {
        mBooksView = booksView;
    }

    @Provides
    @PerActivity
    BooksView provideBooksView() {
        return mBooksView;
    }

}
