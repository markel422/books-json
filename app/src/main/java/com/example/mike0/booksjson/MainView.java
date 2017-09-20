package com.example.mike0.booksjson;

import com.example.mike0.booksjson.model.Books;

import java.util.List;

/**
 * Created by mike0 on 9/17/2017.
 */

public interface MainView {

    void showBooks(List<Books> bookList);

    void getBooks();

    void showErrors();

}
