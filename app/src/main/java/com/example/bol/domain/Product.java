package com.example.bol.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.net.URL;

public class Product implements Parcelable {
    private long mId;
    private String mTitle;
    private int mRating;
    private String mShortDescription;
    private String mSummary;
    private String mImageUrlSmall;
    private String mImageUrlBig;

    private double mNormalPrice;


    public Product() {
    }

    protected Product(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mRating = in.readInt();
        mShortDescription = in.readString();
        mSummary = in.readString();
        mImageUrlSmall = in.readString();
        mImageUrlBig = in.readString();
        mNormalPrice = in.readDouble();
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
        parcel.writeLong(mId);
        parcel.writeString(mTitle);
        parcel.writeInt(mRating);
        parcel.writeString(mShortDescription);
        parcel.writeString(mSummary);
        parcel.writeString(mImageUrlSmall);
        parcel.writeString(mImageUrlBig);
        parcel.writeDouble(mNormalPrice);
    }

    //-------------------SETTERS--------------------
    public void setId(long id) {
        this.mId = id;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }

    public void setShortDescription(String shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public void setSummary(String summary) {
        this.mSummary = summary;
    }

    public void setImageUrlSmall(String imageUrl) {
        this.mImageUrlSmall = imageUrl;
    }

    public void setImageUrlBig(String imageUrlBig) {
        this.mImageUrlBig = imageUrlBig;
    }

    public void setNormalPrice(double normalPrice) {
        this.mNormalPrice = normalPrice;
    }

    //-------------------GETTERS--------------------
    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getRating() {
        return mRating;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getImageUrlSmall() {
        return mImageUrlSmall;
    }

    public String getImageUrlBig() {
        return mImageUrlBig;
    }

    public String getSummary() {
        return mSummary;
    }

    public double getCurrentPrice() {
        return mNormalPrice;
    }
}
