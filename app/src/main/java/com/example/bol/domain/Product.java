package com.example.bol.domain;

import android.util.Log;

import java.io.Serializable;
import java.net.URL;

public class Product implements Serializable {
    private long id;
    private long EAN;
    private String gpc;
    private String title;
    private String subTitle;
    private String specsTag;
    private String summary;
    private int rating;
    private String shortDescription;
    private String longDescrition;
    private URL imageUrlSmall;
    private URL imageUrlBig;

    private double currentPrice;
    private double normalPrice;


    public Product() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEAN(long EAN) {
        this.EAN = EAN;
    }

    public void setGpc(String gpc) {
        this.gpc = gpc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setSpecsTag(String specsTag) {
        this.specsTag = specsTag;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescrition(String longDescrition) {
        this.longDescrition = longDescrition;
    }

    public void setImageUrlSmall(URL imageUrl) {
        this.imageUrlSmall = imageUrl;
    }

    public void setImageUrlBig(URL imageUrlBig) {
        this.imageUrlBig = imageUrlBig;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public long getId() {
        return id;
    }

    public long getEAN() {
        return EAN;
    }

    public String getGpc() {
        return gpc;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getSpecsTag() {
        return specsTag;
    }

    public String getSummary() {
        return summary;
    }

    public int getRating() {
        return rating;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescrition() {
        return longDescrition;
    }

    public URL getImageUrlSmall() {
        return imageUrlSmall;
    }

    public URL getImageUrlBig() {
        return imageUrlBig;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getNormalPrice() {
        return normalPrice;
    }
}
