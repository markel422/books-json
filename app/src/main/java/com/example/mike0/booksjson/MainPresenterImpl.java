package com.example.mike0.booksjson;

import android.content.Context;
import android.widget.Toast;

import com.example.mike0.booksjson.model.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mike0 on 9/17/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView mainView;

    BooksService service;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void init() {
        service = new Retrofit.Builder()
                .baseUrl(BooksService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BooksService.class);
    }

    @Override
    public void getBooks() {
        Call<List<Books>> call = service.getBooks();
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                if (response.isSuccessful()) {
                    mainView.showBooks(response.body());

                } else {
                    mainView.showErrors();
                }
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                mainView.showErrors();
                t.printStackTrace();
            }
        });
    }
}
