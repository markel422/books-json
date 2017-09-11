package com.example.mike0.booksjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mike0.booksjson.model.Books;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    BooksService service;

    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;
    private List<Books> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = new ArrayList<>(0);

        bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_books);
        bookRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        bookRecyclerView.setLayoutManager(linearLayoutManager);

        bookAdapter = new BookAdapter(bookList);
        bookRecyclerView.setAdapter(bookAdapter);
        ButterKnife.bind(this);

        initRandomService();
        fetchBooks();
    }

    private void initRandomService() {
        service = new Retrofit.Builder()
                .baseUrl(BooksService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BooksService.class);
    }

    private void fetchBooks() {
        Call<List<Books>> call = service.getBooks();
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                if (response.isSuccessful()) {
                    List<Books> books = response.body();
                    bookAdapter.updateDataSet(books);
                } else {
                    Toast.makeText(MainActivity.this, "API Error: ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
