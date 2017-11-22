package com.kara4k.mrsofttest.presenter;


import android.database.Cursor;

import com.kara4k.mrsofttest.model.Book;
import com.kara4k.mrsofttest.model.Database;
import com.kara4k.mrsofttest.presenter.mappers.BooksMapper;
import com.kara4k.mrsofttest.view.BooksView;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BooksPresenter implements SingleObserver<List<Book>> {

    @Inject
    BooksView mView;
    @Inject
    Database mDatabase;
    @Inject
    BooksMapper mBooksMapper;

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public BooksPresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onStart() {
        subscribe(() -> mDatabase.getBooks());
    }

    public void onSearch(String text) {
        subscribe(() -> mDatabase.getBooks(text));
    }

    private void subscribe(Callable<Cursor> callable) {
        Single.fromCallable(callable)
                .map(mBooksMapper)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onSuccess(List<Book> books) {
        mView.showBooks(books);
    }

    @Override
    public void onError(Throwable e) {
        mView.showError(e.getMessage());
    }

    public void onDestroy() {
        mCompositeDisposable.dispose();
    }
}
