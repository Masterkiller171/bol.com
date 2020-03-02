package com.example.bol.logic;

import android.app.Activity;
import android.net.Uri;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bol.R;
import com.example.bol.domain.Product;
import com.example.bol.logic.JSONTask;

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

    public void setProductName(String productName) {
        this.productName = productName;
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

    public void showBaseUrlJSON(){
        try {
            ArrayList<Product> products = getUrlContent();

            Product[] data = new Product[products.size()];
            for (int i = 0; i < products.size(); i++) {
                data[i] = products.get(i);
            }

            setupAdapter(data);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setupAdapter(Product[] data){
        RecyclerView recyclerView = mActivity.findViewById(R.id.main_rec_results);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new bolAdapter(data, mActivity);
        recyclerView.setAdapter(mAdapter);
    }

}
