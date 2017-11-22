package com.kara4k.mrsofttest.presenter.mappers;


import android.database.Cursor;

import com.kara4k.mrsofttest.model.Book;
import com.kara4k.mrsofttest.model.Database;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class BooksMapper implements Function<Cursor, List<Book>> {

    @Inject
    public BooksMapper() {
    }

    @Override
    public List<Book> apply(Cursor cursor) throws Exception {
        List<Book> books = new ArrayList<>(cursor.getCount());

        int nameIndex = cursor.getColumnIndex(Database.NAME);
        int authorIndex = cursor.getColumnIndex(Database.AUTHOR);
        int genreIndex = cursor.getColumnIndex(Database.GENRE);
        int chapterIndex = cursor.getColumnIndex(Database.CHAPTER);
        int yearIndex = cursor.getColumnIndex(Database.YEAR);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(nameIndex);
                String author = cursor.getString(authorIndex);
                String genre = cursor.getString(genreIndex);
                int chapter = cursor.getInt(chapterIndex);
                int year = cursor.getInt(yearIndex);

                books.add(new Book(name, author, genre, chapter, year));
            } while (cursor.moveToNext());
        }

        return books;
    }
}
