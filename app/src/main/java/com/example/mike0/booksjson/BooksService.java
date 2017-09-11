package com.example.mike0.booksjson;

import com.example.mike0.booksjson.model.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mike0 on 9/11/2017.
 */

public interface BooksService {
    String BASE_URL = "http://de-coding-test.s3.amazonaws.com";

    @GET("/books.json")
    Call<List<Books>> getBooks();
}
