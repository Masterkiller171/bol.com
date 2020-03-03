package com.example.bol.logic;

import android.app.Activity;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bol.R;
import com.example.bol.dal.NetworkUtils;
import com.example.bol.domain.Product;
import com.example.bol.logic.sorting.HighToLowSort;
import com.example.bol.logic.sorting.LowToHighSort;
import com.example.bol.logic.sorting.ProductSort;

public class NetworkManager {
    private String mProductName;
    private Activity mActivity;
    private NetworkUtils networkUtils;
    private int sortingButtonId;
    private Product[] products;

    public NetworkManager(String mProductName, Activity mActivity, int sortingButtonId) {
        this.mProductName = mProductName;
        this.mActivity = mActivity;
        this.sortingButtonId = sortingButtonId;
    }

    public NetworkManager(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void showProducts(){
        if (isInputEmpty()){
            Toast.makeText(mActivity, "No inuput!", Toast.LENGTH_SHORT).show();
        }else{
            this.networkUtils = new NetworkUtils(this.mProductName, this.mActivity);
            this.products = this.networkUtils.getProducts();
            insertAdapterData(processSortingProducts());
        }
    }

    public Product[] getProducts() {
        return products;
    }

    private Product[] processSortingProducts(){
        ProductSort sorter;
        switch (this.sortingButtonId){
            case R.id.filter_but_hightolow:{
                sorter = new HighToLowSort();
                sorter.setProducts(this.products);
                return sorter.getSortedProducts();
            }
            case R.id.filter_but_lowtohigh:{
                sorter = new LowToHighSort();
                sorter.setProducts(this.products);
                return sorter.getSortedProducts();
            }
        }
        return products;
    }

    public void insertAdapterData(Product[] data){
        this.products = data;
        RecyclerView recyclerView = mActivity.findViewById(R.id.main_rec_results);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new BolAdapter(data, mActivity);
        recyclerView.setAdapter(mAdapter);
    }

    private boolean isInputEmpty(){
        return mProductName.equals("") || mProductName.isEmpty();
    }
}
