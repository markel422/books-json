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

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this);
        presenter.init();
        getBooks();
    }

    private void setUpRecyclerView() {
        bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_books);
        bookRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        bookRecyclerView.setLayoutManager(linearLayoutManager);

        bookAdapter = new BookAdapter(new ArrayList<Books>(0));
        bookRecyclerView.setAdapter(bookAdapter);
    }

    @Override
    public void showBooks(List<Books> bookList) {
        bookAdapter.updateDataSet(bookList);
    }

    @Override
    public void getBooks() {
        presenter.getBooks();
    }

    @Override
    public void showErrors() {
        Toast.makeText(this, "An error occurred.", Toast.LENGTH_SHORT).show();
    }
}
