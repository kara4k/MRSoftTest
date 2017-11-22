package com.kara4k.mrsofttest.view.sortings;


import com.kara4k.mrsofttest.model.Book;

import java.util.Comparator;

public class SortBooks {

    public static Comparator<Book> byName() {
        return (b1, b2) -> b1.getName().compareToIgnoreCase(b2.getName());
    }
}
