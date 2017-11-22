package com.kara4k.mrsofttest.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.kara4k.mrsofttest.R;
import com.kara4k.mrsofttest.di.DaggerBooksComponent;
import com.kara4k.mrsofttest.di.modules.BooksModule;
import com.kara4k.mrsofttest.model.Book;
import com.kara4k.mrsofttest.presenter.BooksPresenter;
import com.kara4k.mrsofttest.view.adapters.BooksAdapter;
import com.kara4k.mrsofttest.view.sortings.SortBooks;

import java.util.List;

import javax.inject.Inject;

public class BooksActivity extends BaseActivity implements BooksView, SearchView.OnQueryTextListener {

    @Inject
    BooksPresenter mPresenter;
    private BooksAdapter mAdapter;
    private SearchView mSearchView;


    @Override
    protected int getContentView() {
        return R.layout.activity_books;
    }

    @Override
    protected void injectDependencies() {
        DaggerBooksComponent.builder()
                .appComponent(getAppComponent())
                .booksModule(new BooksModule(this))
                .build().injectBooksActivity(this);
    }

    @Override
    protected void onViewReady() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new BooksAdapter());

        mPresenter.onStart();
    }

    @Override
    public void showBooks(List<Book> books) {
        mAdapter.setBooks(books);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.books_activity_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();
        mSearchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_sort:
                mAdapter.sort(SortBooks.byName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.onSearch(newText);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!mSearchView.isIconified()) {
            mSearchView.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
