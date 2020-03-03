package com.example.bol.logic.sorting;

import com.example.bol.domain.Product;

public class HighToLowSort implements ProductSort{
    private Product[] products;

    @Override
    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public Product[] getSortedProducts() {
        return bubbleSortProducts(products);
    }

    private Product[] bubbleSortProducts(Product[] products){
        boolean sorted = false;
        Product temp;
        while (!sorted){
            sorted = true;
            for (int i = 0; i < products.length - 1; i++) {
                if (products[i].getCurrentPrice() > products[i+1].getCurrentPrice()){
                    temp = products[i];
                    products[i] = products[i+1];
                    products[i+1] = temp;
                    sorted = false;
                }
            }
        }
        return products;
    }
}
