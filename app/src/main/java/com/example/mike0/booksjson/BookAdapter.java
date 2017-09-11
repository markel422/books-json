package com.example.mike0.booksjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mike0.booksjson.model.Books;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mike0 on 9/11/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    private List<Books> bookList;

    public BookAdapter(List<Books> resultList) {
        this.bookList = resultList;
    }

    public void updateDataSet(List<Books> resultList) {
        this.bookList = resultList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(holder.bookImage.getContext())
                .load(bookList.get(position).getImageURL())
                .into(holder.bookImage);
        holder.bookTitle.setText(bookList.get(position).getTitle());
        holder.bookAuthor.setText(bookList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    private String buildBook(Books result) {
        StringBuilder bookBuilder = new StringBuilder();
        bookBuilder.append(result.getTitle());
        return bookBuilder.toString();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.item_book_image);
            bookTitle = (TextView) itemView.findViewById(R.id.item_book_title);
            bookAuthor = (TextView) itemView.findViewById(R.id.item_book_author);
        }

        /*@Override
        public void onClick(View view) {
            Context context = view.getContext();
            Books result = bookList.get(getAdapterPosition());
            context.startActivity(DetailActivity.getDetailActivityIntent(context, result));
        }*/
    }
}
