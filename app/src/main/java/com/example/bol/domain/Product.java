package com.example.bol.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.net.URL;

public class Product implements Parcelable {
    private long id;
    private String title;
    private int rating;
    private String shortDescription;
    private String summary;
    private String imageUrlSmall;
    private String imageUrlBig;

    private double normalPrice;


    public Product() {
    }

    protected Product(Parcel in) {
        id = in.readLong();
        title = in.readString();
        rating = in.readInt();
        shortDescription = in.readString();
        summary = in.readString();
        imageUrlSmall = in.readString();
        imageUrlBig = in.readString();
        normalPrice = in.readDouble();
    }

    //-------------------CREATOR--------------------
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    //-------------------PARCELABLE--------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeInt(rating);
        parcel.writeString(shortDescription);
        parcel.writeString(summary);
        parcel.writeString(imageUrlSmall);
        parcel.writeString(imageUrlBig);
        parcel.writeDouble(normalPrice);
    }

    //-------------------SETTERS--------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setImageUrlSmall(String imageUrl) {
        this.imageUrlSmall = imageUrl;
    }

    public void setImageUrlBig(String imageUrlBig) {
        this.imageUrlBig = imageUrlBig;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    //-------------------GETTERS--------------------
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public String getImageUrlBig() {
        return imageUrlBig;
    }

    public String getSummary() {
        return summary;
    }

    public double getCurrentPrice() {
        return normalPrice;
    }
}
