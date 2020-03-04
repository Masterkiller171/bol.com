package com.example.bol.logic;

import android.app.Activity;
import android.content.res.Configuration;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bol.R;
import com.example.bol.data.NetworkUtils;
import com.example.bol.data.Preferences;
import com.example.bol.domain.Product;
import com.example.bol.logic.sorting.HighToLowSort;
import com.example.bol.logic.sorting.LowToHighSort;
import com.example.bol.logic.sorting.ProductSort;

import java.util.Locale;

public class NetworkManager {
    private Activity mActivity;
    private NetworkUtils mNetworkUtils;
    private Product[] mProducts;

    private final static String LANGUAGE_CODE = "lang";

    public NetworkManager(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void showProducts(int sortingButtonId, String mProductName){
        if (isInputEmpty(mProductName)){
            Toast.makeText(mActivity, "No inuput!", Toast.LENGTH_SHORT).show();
        }else{
            this.mNetworkUtils = new NetworkUtils(mProductName, this.mActivity);
            this.mProducts = this.mNetworkUtils.getProducts();
            insertAdapterData(processSortingProducts(sortingButtonId));
        }
    }

    public Product[] getProducts() {
        return mProducts;
    }

    private Product[] processSortingProducts(int sortingButtonId){
        ProductSort sorter;
        switch (sortingButtonId){
            case R.id.filter_but_hightolow:{
                sorter = new HighToLowSort(mActivity);
                sorter.setProducts(this.mProducts);
                return sorter.getSortedProducts();
            }
            case R.id.filter_but_lowtohigh:{
                sorter = new LowToHighSort(mActivity);
                sorter.setProducts(this.mProducts);
                return sorter.getSortedProducts();
            }
            case R.id.filter_seek_pricefilter:{
                Toast.makeText(mActivity, "In development ;p", Toast.LENGTH_SHORT).show();
            }
        }
        return mProducts;
    }

    public void insertAdapterData(Product[] data){
        this.mProducts = data;
        RecyclerView recyclerView = mActivity.findViewById(R.id.main_rec_results);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new BolAdapter(data, mActivity);
        recyclerView.setAdapter(mAdapter);
    }

    public void checkLanguage(){
        String langCode = getPreferences(LANGUAGE_CODE);

        if (langCode != null){
            applyLanguage(langCode);
        }else{
            applyLanguage("English");
        }
    }

    private void applyLanguage(String language){
        String langCode = "en";
        switch (language){
            case "Nederlands": langCode = "nl"; break;
            case "English": langCode = "en";break;
        }

        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration configuration = mActivity.getResources().getConfiguration();
        configuration.setLocale(locale);
        mActivity.getResources().updateConfiguration(configuration, mActivity.getResources().getDisplayMetrics());
    }

    public void setPreferences(String key, String value){
        Preferences.storeString(key, value, this.mActivity);
    }

    public String getPreferences(String key){
        return Preferences.retrieveString(key, this.mActivity);
    }
    private boolean isInputEmpty(String mProductName){
        return mProductName.equals("") || mProductName.isEmpty();
    }
}
