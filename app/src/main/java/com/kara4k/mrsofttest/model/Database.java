package com.kara4k.mrsofttest.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import javax.inject.Inject;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "books";

    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String GENRE = "genre";
    public static final String CHAPTER = "chapter";
    public static final String YEAR = "year";

    @Inject
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createBooksTable(sqLiteDatabase);
        insertBooks(sqLiteDatabase);
    }

    private void createBooksTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ("
                + "_id integer primary key autoincrement,"
                + NAME + " text,"
                + AUTHOR + " text,"
                + GENRE + " text,"
                + CHAPTER + " integer,"
                + YEAR + " integer" + ");");
    }

    private void insertBooks(SQLiteDatabase sqLiteDatabase) {
        List<Book> books = DefaultBooks.getBooks();

        for (int i = 0; i < books.size(); i++) {
            ContentValues cv = getContentValues(books.get(i));
            sqLiteDatabase.insert(TABLE_NAME, null, cv);
        }
    }

    public Cursor getBooks() {
        return this.getWritableDatabase()
                .query(TABLE_NAME, null, null, null, null, null, null);
    }

    /**
     * Query books that contains specified text in book name or author name
     *
     * @param query text to compare
     * @return cursor with filtered books
     */
    public Cursor getBooks(String query) {
        String filter = NAME + " LIKE '%" + query + "%'" + " OR "
                + AUTHOR + " LIKE '%" + query + "%'";

        return this.getReadableDatabase()
                .query(TABLE_NAME, null, filter, null, null, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static ContentValues getContentValues(Book book) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, book.getName());
        contentValues.put(AUTHOR, book.getAuthor());
        contentValues.put(GENRE, book.getGenre());
        contentValues.put(CHAPTER, book.getChapter());
        contentValues.put(YEAR, book.getYear());
        return contentValues;
    }
}
