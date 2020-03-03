package com.example.bol.data;

import android.app.Activity;
import android.net.Uri;

import com.example.bol.domain.Product;
import com.example.bol.logic.json.JSONTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    private final static String BOL_BASE_URL = "https://api.bol.com";
    private String productName;
    private String urlContent;
    private Activity mActivity;
    private final static String API_KEY = "25C4742A92BF468EB2BD888FC8FBFF40";

    public NetworkUtils(String productName, Activity mActivity) {
        this.productName = productName;
        this.mActivity = mActivity;
    }

    public ArrayList<Product> getUrlContent() throws ExecutionException, InterruptedException {
        JSONTask task = new JSONTask();
        return task.execute(buildUrl()).get();
    }

    private URL buildUrl(){
        Uri builtUri = Uri.parse(BOL_BASE_URL).buildUpon()
                .appendPath("catalog")
                .appendPath("v4")
                .appendPath("search")
                .appendQueryParameter("apikey", API_KEY)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("q", productName)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public Product[] getProducts(){
        Product[] data = null;
        try {
            ArrayList<Product> products = getUrlContent();

            data = new Product[products.size()];
            for (int i = 0; i < products.size(); i++) {
                data[i] = products.get(i);
            }

            return data;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return data;
    }
}
