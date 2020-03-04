package com.example.bol.logic.sorting;

import android.content.Context;

import com.example.bol.domain.Product;

public class LowToHighSort extends BubbleAlg implements ProductSort {
    private Product[] mProducts;

    public LowToHighSort(Context context) {
        super(context);
    }

    @Override
    public void setProducts(Product[] products) {
        this.mProducts = products;
    }

    @Override
    public Product[] getSortedProducts() {
        return super.getSortedProduct(mProducts);
    }


}
