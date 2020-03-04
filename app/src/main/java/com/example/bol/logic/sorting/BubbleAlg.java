package com.example.bol.logic.sorting;

import android.content.Context;

import com.example.bol.domain.Product;

public abstract class BubbleAlg {
    private Context context;

    public BubbleAlg(Context context) {
        this.context = context;
    }

    protected Product[]  getSortedProduct(Product[] products){
        boolean sorted = false;
        Product temp;
        while (!sorted){
            sorted = true;
            for (int i = products.length - 1; 0 <  i; i--) {
                if (products[i].getCurrentPrice() < products[i-1].getCurrentPrice()){
                    temp = products[i];
                    products[i] = products[i-1];
                    products[i-1] = temp ;
                    sorted = false;
                }
            }
        }
        return products;
    }

    protected Product[] getReversedProduct(Product[] products){
        for (int i = 0; i < products.length / 2; i++) {
            Product product  = products[i];
            products[i] = products[products.length - i- 1];
            products[products.length - i -1] = product;
        }
        return products;
    }
}
