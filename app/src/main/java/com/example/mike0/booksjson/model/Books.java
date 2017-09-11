
package com.example.mike0.booksjson.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Books implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    public final static Parcelable.Creator<Books> CREATOR = new Creator<Books>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Books createFromParcel(Parcel in) {
            Books instance = new Books();
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.author = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageURL = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Books[] newArray(int size) {
            return (new Books[size]);
        }

    }
    ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(author);
        dest.writeValue(imageURL);
    }

    public int describeContents() {
        return  0;
    }

}
