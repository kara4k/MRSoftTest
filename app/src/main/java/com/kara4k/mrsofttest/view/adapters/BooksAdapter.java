package com.kara4k.mrsofttest.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kara4k.mrsofttest.R;
import com.kara4k.mrsofttest.model.Book;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookHolder> {

    private List<Book> mBooksList;

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_book, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        holder.onBind(mBooksList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooksList == null ? 0 : mBooksList.size();
    }

    public void setBooks(List<Book> books) {
        mBooksList = books;
        notifyDataSetChanged();
    }

    public void sort(Comparator<Book> comparator) {
        if (mBooksList == null) return;

        Collections.sort(mBooksList, comparator);
        notifyDataSetChanged();
    }


    class BookHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        TextView mAuthorTextView;
        TextView mGenreTextView;
        TextView mChapterTextView;
        TextView mYearTextView;

        public BookHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.name_text_view);
            mAuthorTextView = itemView.findViewById(R.id.author_text_view);
            mGenreTextView = itemView.findViewById(R.id.genre_text_view);
            mChapterTextView = itemView.findViewById(R.id.chapter_text_view);
            mYearTextView = itemView.findViewById(R.id.year_text_view);
        }

        public void onBind(Book book) {
            mNameTextView.setText(book.getName());
            mAuthorTextView.setText(book.getAuthor());
            mGenreTextView.setText(book.getGenre());
            mChapterTextView.setText(String.valueOf(book.getChapter()));
            mYearTextView.setText(String.valueOf(book.getYear()));
        }
    }
}
