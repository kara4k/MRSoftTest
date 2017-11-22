package com.kara4k.mrsofttest.model;


import java.util.ArrayList;
import java.util.List;

public class DefaultBooks {

    public static List<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>(10);
        books.add(new Book("Book5", "Author1", "Genre2", 5, 1987));
        books.add(new Book("Book1", "Author6", "Genre1", 3, 1984));
        books.add(new Book("Book3", "Author8", "Genre3", 2, 1999));
        books.add(new Book("Book7", "Author4", "Genre5", 1, 2011));
        books.add(new Book("Book9", "Author7", "Genre1", 1, 2002));
        books.add(new Book("Book11", "Author3", "Genre2", 2, 2003));
        books.add(new Book("Book90", "Author3", "Genre1", 3, 1999));
        books.add(new Book("Book13", "Author5", "Genre7", 1, 1981));
        books.add(new Book("Book11", "Author7", "Genre1", 1, 1998));
        books.add(new Book("Book21", "Author9", "Genre10", 2, 2010));
        return books;
    }
}
