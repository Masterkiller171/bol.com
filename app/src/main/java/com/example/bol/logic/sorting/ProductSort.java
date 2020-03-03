package com.example.bol.logic.sorting;

import com.example.bol.domain.Product;

public interface ProductSort {
    void setProducts(Product[] products);
    Product[] getSortedProducts();
}
