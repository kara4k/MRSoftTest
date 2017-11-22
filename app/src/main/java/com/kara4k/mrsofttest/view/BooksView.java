package com.kara4k.mrsofttest.view;


import com.kara4k.mrsofttest.model.Book;

import java.util.List;

public interface BooksView {
    void showBooks(List<Book> books);

    void showError(String message);
}
